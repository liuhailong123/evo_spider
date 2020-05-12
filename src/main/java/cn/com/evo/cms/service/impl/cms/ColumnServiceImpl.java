package cn.com.evo.cms.service.impl.cms;

import cn.com.evo.admin.manage.domain.entity.Account;
import cn.com.evo.admin.manage.domain.entity.AccountRole;
import cn.com.evo.admin.manage.domain.entity.Role;
import cn.com.evo.admin.manage.service.AccountRoleService;
import cn.com.evo.cms.domain.entity.app.AppLockConf;
import cn.com.evo.cms.domain.entity.cms.AuxiliaryFallTemplate;
import cn.com.evo.cms.domain.entity.cms.CatalogueRelation;
import cn.com.evo.cms.domain.entity.cms.Column;
import cn.com.evo.cms.domain.enums.CatalogueRelationType;
import cn.com.evo.cms.domain.enums.ColumnsAuxiliaryType;
import cn.com.evo.cms.repository.cms.ColumnRepository;
import cn.com.evo.cms.service.app.AppLockConfService;
import cn.com.evo.cms.service.cms.*;
import cn.com.evo.cms.utils.ChineseCharUtil;
import com.alibaba.fastjson.JSONObject;
import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.service.AbstractBaseService;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.collect.Lists;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ColumnServiceImpl extends AbstractBaseService<Column, String> implements ColumnService {
    @Autowired
    private Mapper mapper;

    @Autowired
    private ColumnRepository columnRepository;
    @Autowired
    private AccountRoleService accountRoleService;
    @Autowired
    private CatalogueRelationService catalogueRelationService;
    @Autowired
    private AuxiliaryFallTemplateService auxiliaryFallTemplateService;
    @Autowired
    private SourceRelService sourceRelService;
    @Autowired
    private FallTemplateService fallTemplateService;
    @Autowired
    private AppLockConfService appLockConfService;


    @Override
    protected BaseRepository<Column, String> getBaseRepository() {
        return columnRepository;
    }

    @Override
    public void deleteById(String id) {
        Column temp = this.findById(id);

        if (temp.getChildren().size() != 0) {
            throw new RuntimeException("该目录下存在子目录，不允许删除！！！\n如要删除该目录，请先手动删除全部子目录！！！");
        } else {
            // 获取该栏目下挂载的内容数
            List<CatalogueRelation> catalogueRelations = catalogueRelationService.findByAIdAndType(id,
                    CatalogueRelationType.columnsContentRel.getIndex());
            if (catalogueRelations.size() > 0) {
                throw new RuntimeException("该目录下已关联内容，不允许删除！！！\n如要删除该目录，请先手动删除已关联内容！！！");
            }
            // 判断栏目是否被应用加锁
            List<AppLockConf> appLockConfs = appLockConfService.findByContentId(id);
            if (appLockConfs.size() > 0) {
                throw new RuntimeException("该目录下已配置儿童锁，不允许删除！！！\n如要删除该目录，请先手动删除配置的儿童锁！！！");
            }

            // 删除栏目附属信息
            ColumnsAuxiliaryType type = ColumnsAuxiliaryType.val(temp.getType());
            switch (type) {
                case webSite:
                    break;
                case app:
                    break;
                case column:
                    break;
                case waterfall:
                    auxiliaryFallTemplateService.deleteByColumnId(id);
                default:
                    break;
            }

            // 栏目下新增/编辑/删除 子栏目时，将子栏目名称 加入/去除 栏目分类标签
            if (temp.getParent() != null) {
                addOrDeleteColumnClassifyTags(temp.getName(), temp.getParent().getId(), 2);
            }

            // 删除栏目内容关系
            // catalogueRelationService.deleteByColumnId(id);

            // 删除栏目资源关系
            sourceRelService.deleteByColumnId(id);

            // 删除栏目
            super.deleteById(id);
        }
    }

    @Override
    public void save(Object main, Object other, String columnPId) {
        try {
            // 保存栏目数据
            Column entity = mapper.map(main, Column.class);
            Column parent = this.findById(columnPId);
            entity.setParent(parent);
            if (parent != null) {
                entity.setLevel(parent.getLevel() + 1);
            } else {
                entity.setLevel(1);
            }

            // 设置拼音
            String name = entity.getName();
            // 去除中文中的特殊字符
            String nameTemp = ChineseCharUtil.removeSpecialChar(name);
            // 获取全拼
            String nameLongPinyin = PinyinHelper.convertToPinyinString(nameTemp, "", PinyinFormat.WITHOUT_TONE);
            // 获取简拼
            String nameShortPinyin = PinyinHelper.getShortPinyin(nameTemp);
            entity.setNameSpellLong(nameLongPinyin);
            entity.setNameSpellShort(nameShortPinyin);
            entity.setColumnCode(getCode(entity));
            super.save(entity);

            // 保存栏目挂载图片信息
            JSONObject jo = JSONObject.parseObject(main.toString());
            jo.put("id", entity.getId());
            sourceRelService.saveSourceRel(jo);

            // 保存栏目附属信息前，删除 该栏目原有附属信息
            deleteColumnAuxiliary(entity.getId());

            // 保存附属信息数据
            ColumnsAuxiliaryType type = ColumnsAuxiliaryType.val(entity.getType());
            switch (type) {
                case webSite:
                    break;
                case app:
                    break;
                case column:
                    break;
                case waterfall:
                    JSONObject auxiliaryFallTemplateJo = JSONObject.parseObject(other.toString());
                    AuxiliaryFallTemplate auxiliaryFallTemplate = new AuxiliaryFallTemplate();
                    auxiliaryFallTemplate.setColumn(entity);
                    auxiliaryFallTemplate.setFallTemplate(
                            fallTemplateService.findById(auxiliaryFallTemplateJo.getString("fallTemplateId")));
                    auxiliaryFallTemplate.setIsShowTitle(auxiliaryFallTemplateJo.getInteger("isShowTitle"));
                    auxiliaryFallTemplateService.save(auxiliaryFallTemplate);
                    break;
                default:
                    break;
            }

            // 栏目下新增/编辑 子栏目时，将子栏目名称 加入 栏目分类标签
            addOrDeleteColumnClassifyTags(name, columnPId, 1);

        } catch (Exception e) {
            throw new RuntimeException("保存栏目以及栏目附属信息异常", e);
        }
    }

    private void addOrDeleteColumnClassifyTags(String name, String columnPId, int type) {
        if (!"0".equals(columnPId)) {// 如果栏目id为0，则该栏目为虚拟目录
            Column entity = this.findById(columnPId);
            if (entity.getLevel() == 3) {// 该目录级别为栏目
                String classifyTags = entity.getClassifyTags();
                if ("".equals(classifyTags) || classifyTags == null) {
                    classifyTags = "null";
                }
                if (classifyTags.indexOf(name) == -1) {// 不包含
                    if (type == 1) {// 添加分类标签
                        if (!"".equals(classifyTags) && classifyTags != null && !"null".equals(classifyTags)) {
                            classifyTags += "," + name;
                        } else {
                            classifyTags = name;
                        }
                    }
                } else {// 包含
                    if (type == 2) {// 删除分类标签
                        classifyTags = classifyTags.replaceAll(name, "");
                        classifyTags = classifyTags.replaceAll(",,", ",");
                    }
                }
                entity.setClassifyTags(classifyTags);
                this.update(entity);
            }
        }
    }

    @Override
    public void update(Object main, Object other, String columnPId) {
        try {
            // 更新栏目数据
            Column entity = mapper.map(main, Column.class);
            Column temp = this.findById(entity.getId());
            Column parent = this.findById(columnPId);
            entity.setCreateDate(temp.getCreateDate());
            entity.setChildren(temp.getChildren());
            entity.setParent(parent);

            // 设置拼音
            String name = entity.getName();
            // 去除中文中的特殊字符
            String nameTemp = ChineseCharUtil.removeSpecialChar(name);
            // 获取全拼
            String nameLongPinyin = PinyinHelper.convertToPinyinString(nameTemp, "", PinyinFormat.WITHOUT_TONE);
            // 获取简拼
            String nameShortPinyin = PinyinHelper.getShortPinyin(nameTemp);
            entity.setNameSpellLong(nameLongPinyin);
            entity.setNameSpellShort(nameShortPinyin);
            super.update(entity);

            // 更新栏目挂载图片信息
            sourceRelService.saveSourceRel(main);

            // 更新栏目附属信息前，删除 该栏目原有附属信息
            deleteColumnAuxiliary(entity.getId());

            // 更新附属信息数据
            ColumnsAuxiliaryType type = ColumnsAuxiliaryType.val(entity.getType());
            switch (type) {
                case webSite:
                    break;
                case app:
                    break;
                case column:
                    break;
                case waterfall:
                    JSONObject auxiliaryFallTemplateJo = JSONObject.parseObject(other.toString());
                    AuxiliaryFallTemplate auxiliaryFallTemplate = new AuxiliaryFallTemplate();
                    auxiliaryFallTemplate.setColumn(entity);
                    auxiliaryFallTemplate.setFallTemplate(
                            fallTemplateService.findById(auxiliaryFallTemplateJo.getString("fallTemplateId")));
                    auxiliaryFallTemplate.setIsShowTitle(auxiliaryFallTemplateJo.getInteger("isShowTitle"));
                    auxiliaryFallTemplateService.save(auxiliaryFallTemplate);
                    break;
                default:
                    break;
            }

            // 栏目下新增/编辑 子栏目时，将子栏目名称 加入 栏目分类标签
            addOrDeleteColumnClassifyTags(name, columnPId, 1);

        } catch (Exception e) {
            throw new RuntimeException("更新栏目以及栏目附属信息异常", e);
        }
    }

    /**
     * 根据栏目id删除 该栏目原有附属信息
     *
     * @param id
     */
    private void deleteColumnAuxiliary(String id) {
        // 2 删除 瀑布流模版附属信息
        auxiliaryFallTemplateService.deleteByColumnId(id);
    }

    public String getCode(Column entity) {
        String code = "";
        if (entity.getParent() == null) {
            List<Column> entities = columnRepository.findByLevelOrderBySort(1);
            code = 10000 + entities.size() + "";
        } else {
            String pId = entity.getParent().getId();
            Column parent = this.findById(pId);
            List<Column> entities = columnRepository.findByLevelAndParentIdOrderBySort(parent.getLevel() + 1, pId);
            code = parent.getColumnCode() + String.valueOf((10000 + entities.size()));
        }

        return code;
    }

    @Override
    public List<Column> findAllColumnBySiteId(String siteId) {
        List<Column> columns = Lists.newArrayList();
        Column column = this.findById(siteId);
        columns = columnRepository.findLikeByCode(column.getColumnCode() + "%");
        return columns;
    }

    @Override
    public List<Column> findByAccount(Account account) {
        List<Column> columns = Lists.newArrayList();
        List<AccountRole> accountRoles = accountRoleService.findByAccountId(account.getId());
        for (AccountRole accountRole : accountRoles) {
            Role role = accountRole.getRole();
            if ("Super_Admin".equals(role.getCode())) {
                columns = this.findByLevel(1);
                break;
            } else {
                List<CatalogueRelation> catalogueRelations = catalogueRelationService.findByAIdAndType(role.getId(), 1);
                for (CatalogueRelation catalogueRelation : catalogueRelations) {
                    Column column = this.findById(catalogueRelation.getBId());
                    columns.add(column);
                }
            }

        }
        return columns;
    }

    private List<Column> findByLevel(int level) {
        return columnRepository.findByLevelOrderBySort(level);
    }

    @Override
    public void assign(String roleId, String[] siteIds) {
        for (String siteId : siteIds) {
            CatalogueRelation catalogueRelation = catalogueRelationService.getByAIdAndBIdAndType(roleId, siteId, 1);
            if (catalogueRelation == null) {
                catalogueRelation = new CatalogueRelation();
                catalogueRelation.setAId(roleId);
                catalogueRelation.setBId(siteId);
                catalogueRelation.setType(1);
                catalogueRelationService.save(catalogueRelation);
            }
        }
    }

    @Override
    public List<Column> findByPIdAndType(String columnId, Integer type, Integer start, Integer pageSize) {
        return columnRepository.findByPIdAndType(columnId, type, start, pageSize);
    }

    @Override
    public List<Column> findByPId(String columnId) {
        return columnRepository.findByPIdAndEnable(columnId, 1);
    }

    @Override
    public Long findWaterfallCountByColumnIdAndType(String columnId, int type) {
        return columnRepository.findWaterfallCountByColumnIdAndType(columnId, type);
    }

    @Override
    public List<Column> findByNameAndEnable(String columnsName, int enable) {
        return columnRepository.findByNameAndEnable(columnsName, enable);
    }

    @Override
    public void setRecommend(String columnId, Integer type) {
        Column column = this.findById(columnId);
        if (type == 1) {
            column.setIsRecommend(1);
        } else {
            column.setIsRecommend(0);
        }
        super.update(column);
    }

    @Override
    public String getAppId(String columnId) {
        Column column = this.findById(columnId);
        String code = column.getColumnCode();
        Column app = columnRepository.getByColumnCode(code.substring(0, 5));
        return app.getId();
    }

    @Override
    public Column getByThirdCode(String code) {
        return columnRepository.getByThirdCode(code);
    }
}
