!
function(global, factory) {
    "object" == typeof module && "object" == typeof module.exports ? module.exports = global.document ? factory(global, !0) : function(w) {
        if (!w.document) throw new Error("jQuery requires a window with a document");
        return factory(w)
    }: factory(global)
} ("undefined" != typeof window ? window: this,
function(window, noGlobal) {
    function isArraylike(obj) {
        var length = obj.length,
        type = jQuery.type(obj);
        return "function" !== type && !jQuery.isWindow(obj) && (!(1 !== obj.nodeType || !length) || ("array" === type || 0 === length || "number" == typeof length && length > 0 && length - 1 in obj))
    }
    function createOptions(options) {
        var object = optionsCache[options] = {};
        return jQuery.each(options.match(rnotwhite) || [],
        function(_, flag) {
            object[flag] = !0
        }),
        object
    }
    function detach() {
        document.addEventListener ? (document.removeEventListener("DOMContentLoaded", completed, !1), window.removeEventListener("load", completed, !1)) : (document.detachEvent("onreadystatechange", completed), window.detachEvent("onload", completed))
    }
    function completed() { (document.addEventListener || "load" === event.type || "complete" === document.readyState) && (detach(), jQuery.ready())
    }
    function dataAttr(elem, key, data) {
        if (void 0 === data && 1 === elem.nodeType) {
            var name = "data-" + key.replace(rmultiDash, "-$1").toLowerCase();
            if ("string" == typeof(data = elem.getAttribute(name))) {
                try {
                    data = "true" === data || "false" !== data && ("null" === data ? null: +data + "" === data ? +data: rbrace.test(data) ? jQuery.parseJSON(data) : data)
                } catch(e) {}
                jQuery.data(elem, key, data)
            } else data = void 0
        }
        return data
    }
    function isEmptyDataObject(obj) {
        var name;
        for (name in obj) if (("data" !== name || !jQuery.isEmptyObject(obj[name])) && "toJSON" !== name) return ! 1;
        return ! 0
    }
    function internalData(elem, name, data, pvt) {
        if (jQuery.acceptData(elem)) {
            var ret, thisCache, internalKey = jQuery.expando,
            isNode = elem.nodeType,
            cache = isNode ? jQuery.cache: elem,
            id = isNode ? elem[internalKey] : elem[internalKey] && internalKey;
            if (id && cache[id] && (pvt || cache[id].data) || void 0 !== data || "string" != typeof name) return id || (id = isNode ? elem[internalKey] = deletedIds.pop() || jQuery.guid++:internalKey),
            cache[id] || (cache[id] = isNode ? {}: {
                toJSON: jQuery.noop
            }),
            "object" != typeof name && "function" != typeof name || (pvt ? cache[id] = jQuery.extend(cache[id], name) : cache[id].data = jQuery.extend(cache[id].data, name)),
            thisCache = cache[id],
            pvt || (thisCache.data || (thisCache.data = {}), thisCache = thisCache.data),
            void 0 !== data && (thisCache[jQuery.camelCase(name)] = data),
            "string" == typeof name ? null == (ret = thisCache[name]) && (ret = thisCache[jQuery.camelCase(name)]) : ret = thisCache,
            ret
        }
    }
    function internalRemoveData(elem, name, pvt) {
        if (jQuery.acceptData(elem)) {
            var thisCache, i, isNode = elem.nodeType,
            cache = isNode ? jQuery.cache: elem,
            id = isNode ? elem[jQuery.expando] : jQuery.expando;
            if (cache[id]) {
                if (name && (thisCache = pvt ? cache[id] : cache[id].data)) {
                    jQuery.isArray(name) ? name = name.concat(jQuery.map(name, jQuery.camelCase)) : name in thisCache ? name = [name] : (name = jQuery.camelCase(name), name = name in thisCache ? [name] : name.split(" ")),
                    i = name.length;
                    for (; i--;) delete thisCache[name[i]];
                    if (pvt ? !isEmptyDataObject(thisCache) : !jQuery.isEmptyObject(thisCache)) return
                } (pvt || (delete cache[id].data, isEmptyDataObject(cache[id]))) && (isNode ? jQuery.cleanData([elem], !0) : support.deleteExpando || cache != cache.window ? delete cache[id] : cache[id] = null)
            }
        }
    }
    function returnTrue() {
        return ! 0
    }
    function returnFalse() {
        return ! 1
    }
    function addToPrefiltersOrTransports(structure) {
        return function(dataTypeExpression, func) {
            "string" != typeof dataTypeExpression && (func = dataTypeExpression, dataTypeExpression = "*");
            var dataType, i = 0,
            dataTypes = dataTypeExpression.toLowerCase().match(rnotwhite) || [];
            if (jQuery.isFunction(func)) for (; dataType = dataTypes[i++];)"+" === dataType.charAt(0) ? (dataType = dataType.slice(1) || "*", (structure[dataType] = structure[dataType] || []).unshift(func)) : (structure[dataType] = structure[dataType] || []).push(func)
        }
    }
    function inspectPrefiltersOrTransports(structure, options, originalOptions, jqXHR) {
        function inspect(dataType) {
            var selected;
            return inspected[dataType] = !0,
            jQuery.each(structure[dataType] || [],
            function(_, prefilterOrFactory) {
                var dataTypeOrTransport = prefilterOrFactory(options, originalOptions, jqXHR);
                return "string" != typeof dataTypeOrTransport || seekingTransport || inspected[dataTypeOrTransport] ? seekingTransport ? !(selected = dataTypeOrTransport) : void 0 : (options.dataTypes.unshift(dataTypeOrTransport), inspect(dataTypeOrTransport), !1)
            }),
            selected
        }
        var inspected = {},
        seekingTransport = structure === transports;
        return inspect(options.dataTypes[0]) || !inspected["*"] && inspect("*")
    }
    function ajaxExtend(target, src) {
        var deep, key, flatOptions = jQuery.ajaxSettings.flatOptions || {};
        for (key in src) void 0 !== src[key] && ((flatOptions[key] ? target: deep || (deep = {}))[key] = src[key]);
        return deep && jQuery.extend(!0, target, deep),
        target
    }
    function ajaxHandleResponses(s, jqXHR, responses) {
        for (var firstDataType, ct, finalDataType, type, contents = s.contents,
        dataTypes = s.dataTypes;
        "*" === dataTypes[0];) dataTypes.shift(),
        void 0 === ct && (ct = s.mimeType || jqXHR.getResponseHeader("Content-Type"));
        if (ct) for (type in contents) if (contents[type] && contents[type].test(ct)) {
            dataTypes.unshift(type);
            break
        }
        if (dataTypes[0] in responses) finalDataType = dataTypes[0];
        else {
            for (type in responses) {
                if (!dataTypes[0] || s.converters[type + " " + dataTypes[0]]) {
                    finalDataType = type;
                    break
                }
                firstDataType || (firstDataType = type)
            }
            finalDataType = finalDataType || firstDataType
        }
        if (finalDataType) return finalDataType !== dataTypes[0] && dataTypes.unshift(finalDataType),
        responses[finalDataType]
    }
    function ajaxConvert(s, response, jqXHR, isSuccess) {
        var conv2, current, conv, tmp, prev, converters = {},
        dataTypes = s.dataTypes.slice();
        if (dataTypes[1]) for (conv in s.converters) converters[conv.toLowerCase()] = s.converters[conv];
        for (current = dataTypes.shift(); current;) if (s.responseFields[current] && (jqXHR[s.responseFields[current]] = response), !prev && isSuccess && s.dataFilter && (response = s.dataFilter(response, s.dataType)), prev = current, current = dataTypes.shift()) if ("*" === current) current = prev;
        else if ("*" !== prev && prev !== current) {
            if (! (conv = converters[prev + " " + current] || converters["* " + current])) for (conv2 in converters) if (tmp = conv2.split(" "), tmp[1] === current && (conv = converters[prev + " " + tmp[0]] || converters["* " + tmp[0]])) { ! 0 === conv ? conv = converters[conv2] : !0 !== converters[conv2] && (current = tmp[0], dataTypes.unshift(tmp[1]));
                break
            }
            if (!0 !== conv) if (conv && s.throws) response = conv(response);
            else try {
                response = conv(response)
            } catch(e) {
                return {
                    state: "parsererror",
                    error: conv ? e: "No conversion from " + prev + " to " + current
                }
            }
        }
        return {
            state: "success",
            data: response
        }
    }
    function buildParams(prefix, obj, traditional, add) {
        var name;
        if (jQuery.isArray(obj)) jQuery.each(obj,
        function(i, v) {
            traditional || rbracket.test(prefix) ? add(prefix, v) : buildParams(prefix + "[" + ("object" == typeof v ? i: "") + "]", v, traditional, add)
        });
        else if (traditional || "object" !== jQuery.type(obj)) add(prefix, obj);
        else for (name in obj) buildParams(prefix + "[" + name + "]", obj[name], traditional, add)
    }
    function createStandardXHR() {
        try {
            return new window.XMLHttpRequest
        } catch(e) {}
    }
    function createActiveXHR() {
        try {
            return new window.ActiveXObject("Microsoft.XMLHTTP")
        } catch(e) {}
    }
    var deletedIds = [],
    slice = deletedIds.slice,
    concat = deletedIds.concat,
    push = deletedIds.push,
    indexOf = deletedIds.indexOf,
    class2type = {},
    toString = class2type.toString,
    hasOwn = class2type.hasOwnProperty,
    support = {},
    version = "1.11.1 -css,-css/addGetHookIf,-css/curCSS,-css/defaultDisplay,-css/hiddenVisibleSelectors,-css/support,-css/swap,-css/var/cssExpand,-css/var/isHidden,-css/var/rmargin,-css/var/rnumnonpx,-effects,-effects/Tween,-effects/animatedSelector,-effects/support,-dimensions,-offset,-deprecated,-event-alias,-wrap",
    jQuery = function(selector, context) {
        return new jQuery.fn.init(selector, context)
    },
    fcamelCase = function(all, letter) {
        return letter.toUpperCase()
    };
    jQuery.fn = jQuery.prototype = {
        jquery: version,
        constructor: jQuery,
        selector: "",
        length: 0,
        toArray: function() {
            return slice.call(this)
        },
        get: function(num) {
            return null != num ? num < 0 ? this[num + this.length] : this[num] : slice.call(this)
        },
        pushStack: function(elems) {
            var ret = jQuery.merge(this.constructor(), elems);
            return ret.prevObject = this,
            ret.context = this.context,
            ret
        },
        each: function(callback, args) {
            return jQuery.each(this, callback, args)
        },
        map: function(callback) {
            return this.pushStack(jQuery.map(this,
            function(elem, i) {
                return callback.call(elem, i, elem)
            }))
        },
        slice: function() {
            return this.pushStack(slice.apply(this, arguments))
        },
        first: function() {
            return this.eq(0)
        },
        last: function() {
            return this.eq( - 1)
        },
        eq: function(i) {
            var len = this.length,
            j = +i + (i < 0 ? len: 0);
            return this.pushStack(j >= 0 && j < len ? [this[j]] : [])
        },
        end: function() {
            return this.prevObject || this.constructor(null)
        },
        push: push,
        sort: deletedIds.sort,
        splice: deletedIds.splice
    },
    jQuery.extend = jQuery.fn.extend = function() {
        var src, copyIsArray, copy, name, options, clone, target = arguments[0] || {},
        i = 1,
        length = arguments.length,
        deep = !1;
        for ("boolean" == typeof target && (deep = target, target = arguments[i] || {},
        i++), "object" == typeof target || jQuery.isFunction(target) || (target = {}), i === length && (target = this, i--); i < length; i++) if (null != (options = arguments[i])) for (name in options) src = target[name],
        copy = options[name],
        target !== copy && (deep && copy && (jQuery.isPlainObject(copy) || (copyIsArray = jQuery.isArray(copy))) ? (copyIsArray ? (copyIsArray = !1, clone = src && jQuery.isArray(src) ? src: []) : clone = src && jQuery.isPlainObject(src) ? src: {},
        target[name] = jQuery.extend(deep, clone, copy)) : void 0 !== copy && (target[name] = copy));
        return target
    },
    jQuery.extend({
        expando: "jQuery" + (version + Math.random()).replace(/\D/g, ""),
        isReady: !0,
        error: function(msg) {
            throw new Error(msg)
        },
        noop: function() {},
        isFunction: function(obj) {
            return "function" === jQuery.type(obj)
        },
        isArray: Array.isArray ||
        function(obj) {
            return "array" === jQuery.type(obj)
        },
        isWindow: function(obj) {
            return null != obj && obj == obj.window
        },
        isNumeric: function(obj) {
            return ! jQuery.isArray(obj) && obj - parseFloat(obj) >= 0
        },
        isEmptyObject: function(obj) {
            var name;
            for (name in obj) return ! 1;
            return ! 0
        },
        isPlainObject: function(obj) {
            var key;
            if (!obj || "object" !== jQuery.type(obj) || obj.nodeType || jQuery.isWindow(obj)) return ! 1;
            try {
                if (obj.constructor && !hasOwn.call(obj, "constructor") && !hasOwn.call(obj.constructor.prototype, "isPrototypeOf")) return ! 1
            } catch(e) {
                return ! 1
            }
            if (support.ownLast) for (key in obj) return hasOwn.call(obj, key);
            for (key in obj);
            return void 0 === key || hasOwn.call(obj, key)
        },
        type: function(obj) {
            return null == obj ? obj + "": "object" == typeof obj || "function" == typeof obj ? class2type[toString.call(obj)] || "object": typeof obj
        },
        globalEval: function(data) {
            data && jQuery.trim(data) && (window.execScript ||
            function(data) {
                window.eval.call(window, data)
            })(data)
        },
        camelCase: function(string) {
            return string.replace(/^-ms-/, "ms-").replace(/-([\da-z])/gi, fcamelCase)
        },
        nodeName: function(elem, name) {
            return elem.nodeName && elem.nodeName.toLowerCase() === name.toLowerCase()
        },
        each: function(obj, callback, args) {
            var i = 0,
            length = obj.length,
            isArray = isArraylike(obj);
            if (args) {
                if (isArray) for (; i < length && !1 !== callback.apply(obj[i], args); i++);
                else for (i in obj) if (!1 === callback.apply(obj[i], args)) break
            } else if (isArray) for (; i < length && !1 !== callback.call(obj[i], i, obj[i]); i++);
            else for (i in obj) if (!1 === callback.call(obj[i], i, obj[i])) break;
            return obj
        },
        trim: function(text) {
            return null == text ? "": (text + "").replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, "")
        },
        makeArray: function(arr, results) {
            var ret = results || [];
            return null != arr && (isArraylike(Object(arr)) ? jQuery.merge(ret, "string" == typeof arr ? [arr] : arr) : push.call(ret, arr)),
            ret
        },
        inArray: function(elem, arr, i) {
            var len;
            if (arr) {
                if (indexOf) return indexOf.call(arr, elem, i);
                for (len = arr.length, i = i ? i < 0 ? Math.max(0, len + i) : i: 0; i < len; i++) if (i in arr && arr[i] === elem) return i
            }
            return - 1
        },
        merge: function(first, second) {
            for (var len = +second.length,
            j = 0,
            i = first.length; j < len;) first[i++] = second[j++];
            if (len !== len) for (; void 0 !== second[j];) first[i++] = second[j++];
            return first.length = i,
            first
        },
        grep: function(elems, callback, invert) {
            for (var matches = [], i = 0, length = elems.length, callbackExpect = !invert; i < length; i++) ! callback(elems[i], i) !== callbackExpect && matches.push(elems[i]);
            return matches
        },
        map: function(elems, callback, arg) {
            var value, i = 0,
            length = elems.length,
            isArray = isArraylike(elems),
            ret = [];
            if (isArray) for (; i < length; i++) null != (value = callback(elems[i], i, arg)) && ret.push(value);
            else for (i in elems) null != (value = callback(elems[i], i, arg)) && ret.push(value);
            return concat.apply([], ret)
        },
        guid: 1,
        proxy: function(fn, context) {
            var args, proxy, tmp;
            if ("string" == typeof context && (tmp = fn[context], context = fn, fn = tmp), jQuery.isFunction(fn)) return args = slice.call(arguments, 2),
            proxy = function() {
                return fn.apply(context || this, args.concat(slice.call(arguments)))
            },
            proxy.guid = fn.guid = fn.guid || jQuery.guid++,
            proxy
        },
        now: function() {
            return + new Date
        },
        support: support
    }),
    jQuery.each("Boolean Number String Function Array Date RegExp Object Error".split(" "),
    function(i, name) {
        class2type["[object " + name + "]"] = name.toLowerCase()
    });
    var rootjQuery, document = window.document,
    rquickExpr = /^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]*))$/; (jQuery.fn.init = function(selector, context) {
        var match, elem;
        if (!selector) return this;
        if ("string" == typeof selector) {
            if (! (match = "<" === selector.charAt(0) && ">" === selector.charAt(selector.length - 1) && selector.length >= 3 ? [null, selector, null] : rquickExpr.exec(selector)) || !match[1] && context) return ! context || context.jquery ? (context || rootjQuery).find(selector) : this.constructor(context).find(selector);
            if (match[1]) {
                if (context = context instanceof jQuery ? context[0] : context, jQuery.merge(this, jQuery.parseHTML(match[1], context && context.nodeType ? context.ownerDocument || context: document, !0)), rsingleTag.test(match[1]) && jQuery.isPlainObject(context)) for (match in context) jQuery.isFunction(this[match]) ? this[match](context[match]) : this.attr(match, context[match]);
                return this
            }
            if ((elem = document.getElementById(match[2])) && elem.parentNode) {
                if (elem.id !== match[2]) return rootjQuery.find(selector);
                this.length = 1,
                this[0] = elem
            }
            return this.context = document,
            this.selector = selector,
            this
        }
        return selector.nodeType ? (this.context = this[0] = selector, this.length = 1, this) : jQuery.isFunction(selector) ? void 0 !== rootjQuery.ready ? rootjQuery.ready(selector) : selector(jQuery) : (void 0 !== selector.selector && (this.selector = selector.selector, this.context = selector.context), jQuery.makeArray(selector, this))
    }).prototype = jQuery.fn,
    rootjQuery = jQuery(document);
    var rnotwhite = /\S+/g,
    optionsCache = {};
    jQuery.Callbacks = function(options) {
        options = "string" == typeof options ? optionsCache[options] || createOptions(options) : jQuery.extend({},
        options);
        var firing, memory, fired, firingLength, firingIndex, firingStart, list = [],
        stack = !options.once && [],
        fire = function(data) {
            for (memory = options.memory && data, fired = !0, firingIndex = firingStart || 0, firingStart = 0, firingLength = list.length, firing = !0; list && firingIndex < firingLength; firingIndex++) if (!1 === list[firingIndex].apply(data[0], data[1]) && options.stopOnFalse) {
                memory = !1;
                break
            }
            firing = !1,
            list && (stack ? stack.length && fire(stack.shift()) : memory ? list = [] : self.disable())
        },
        self = {
            add: function() {
                if (list) {
                    var start = list.length; !
                    function add(args) {
                        jQuery.each(args,
                        function(_, arg) {
                            var type = jQuery.type(arg);
                            "function" === type ? options.unique && self.has(arg) || list.push(arg) : arg && arg.length && "string" !== type && add(arg)
                        })
                    } (arguments),
                    firing ? firingLength = list.length: memory && (firingStart = start, fire(memory))
                }
                return this
            },
            remove: function() {
                return list && jQuery.each(arguments,
                function(_, arg) {
                    for (var index; (index = jQuery.inArray(arg, list, index)) > -1;) list.splice(index, 1),
                    firing && (index <= firingLength && firingLength--, index <= firingIndex && firingIndex--)
                }),
                this
            },
            has: function(fn) {
                return fn ? jQuery.inArray(fn, list) > -1 : !(!list || !list.length)
            },
            empty: function() {
                return list = [],
                firingLength = 0,
                this
            },
            disable: function() {
                return list = stack = memory = void 0,
                this
            },
            disabled: function() {
                return ! list
            },
            lock: function() {
                return stack = void 0,
                memory || self.disable(),
                this
            },
            locked: function() {
                return ! stack
            },
            fireWith: function(context, args) {
                return ! list || fired && !stack || (args = args || [], args = [context, args.slice ? args.slice() : args], firing ? stack.push(args) : fire(args)),
                this
            },
            fire: function() {
                return self.fireWith(this, arguments),
                this
            },
            fired: function() {
                return !! fired
            }
        };
        return self
    },
    jQuery.extend({
        Deferred: function(func) {
            var tuples = [["resolve", "done", jQuery.Callbacks("once memory"), "resolved"], ["reject", "fail", jQuery.Callbacks("once memory"), "rejected"], ["notify", "progress", jQuery.Callbacks("memory")]],
            state = "pending",
            promise = {
                state: function() {
                    return state
                },
                always: function() {
                    return deferred.done(arguments).fail(arguments),
                    this
                },
                then: function() {
                    var fns = arguments;
                    return jQuery.Deferred(function(newDefer) {
                        jQuery.each(tuples,
                        function(i, tuple) {
                            var fn = jQuery.isFunction(fns[i]) && fns[i];
                            deferred[tuple[1]](function() {
                                var returned = fn && fn.apply(this, arguments);
                                returned && jQuery.isFunction(returned.promise) ? returned.promise().done(newDefer.resolve).fail(newDefer.reject).progress(newDefer.notify) : newDefer[tuple[0] + "With"](this === promise ? newDefer.promise() : this, fn ? [returned] : arguments)
                            })
                        }),
                        fns = null
                    }).promise()
                },
                promise: function(obj) {
                    return null != obj ? jQuery.extend(obj, promise) : promise
                }
            },
            deferred = {};
            return promise.pipe = promise.then,
            jQuery.each(tuples,
            function(i, tuple) {
                var list = tuple[2],
                stateString = tuple[3];
                promise[tuple[1]] = list.add,
                stateString && list.add(function() {
                    state = stateString
                },
                tuples[1 ^ i][2].disable, tuples[2][2].lock),
                deferred[tuple[0]] = function() {
                    return deferred[tuple[0] + "With"](this === deferred ? promise: this, arguments),
                    this
                },
                deferred[tuple[0] + "With"] = list.fireWith
            }),
            promise.promise(deferred),
            func && func.call(deferred, deferred),
            deferred
        },
        when: function(subordinate) {
            var progressValues, progressContexts, resolveContexts, i = 0,
            resolveValues = slice.call(arguments),
            length = resolveValues.length,
            remaining = 1 !== length || subordinate && jQuery.isFunction(subordinate.promise) ? length: 0,
            deferred = 1 === remaining ? subordinate: jQuery.Deferred(),
            updateFunc = function(i, contexts, values) {
                return function(value) {
                    contexts[i] = this,
                    values[i] = arguments.length > 1 ? slice.call(arguments) : value,
                    values === progressValues ? deferred.notifyWith(contexts, values) : --remaining || deferred.resolveWith(contexts, values)
                }
            };
            if (length > 1) for (progressValues = new Array(length), progressContexts = new Array(length), resolveContexts = new Array(length); i < length; i++) resolveValues[i] && jQuery.isFunction(resolveValues[i].promise) ? resolveValues[i].promise().done(updateFunc(i, resolveContexts, resolveValues)).fail(deferred.reject).progress(updateFunc(i, progressContexts, progressValues)) : --remaining;
            return remaining || deferred.resolveWith(resolveContexts, resolveValues),
            deferred.promise()
        }
    });
    var readyList;
    jQuery.fn.ready = function(fn) {
        return jQuery.ready.promise().done(fn),
        this
    },
    jQuery.extend({
        isReady: !1,
        readyWait: 1,
        holdReady: function(hold) {
            hold ? jQuery.readyWait++:jQuery.ready(!0)
        },
        ready: function(wait) {
            if (!0 === wait ? !--jQuery.readyWait: !jQuery.isReady) {
                if (!document.body) return setTimeout(jQuery.ready);
                jQuery.isReady = !0,
                !0 !== wait && --jQuery.readyWait > 0 || (readyList.resolveWith(document, [jQuery]), jQuery.fn.triggerHandler && (jQuery(document).triggerHandler("ready"), jQuery(document).off("ready")))
            }
        }
    }),
    jQuery.ready.promise = function(obj) {
        if (!readyList) if (readyList = jQuery.Deferred(), "complete" === document.readyState) setTimeout(jQuery.ready);
        else if (document.addEventListener) document.addEventListener("DOMContentLoaded", completed, !1),
        window.addEventListener("load", completed, !1);
        else {
            document.attachEvent("onreadystatechange", completed),
            window.attachEvent("onload", completed);
            var top = !1;
            try {
                top = null == window.frameElement && document.documentElement
            } catch(e) {}
            top && top.doScroll &&
            function doScrollCheck() {
                if (!jQuery.isReady) {
                    try {
                        top.doScroll("left")
                    } catch(e) {
                        return setTimeout(doScrollCheck, 50)
                    }
                    detach(),
                    jQuery.ready()
                }
            } ()
        }
        return readyList.promise(obj)
    };
    var i;
    for (i in jQuery(support)) break;
    support.ownLast = "0" !== i,
    support.inlineBlockNeedsLayout = !1,
    jQuery(function() {
        var val, div, body, container; (body = document.getElementsByTagName("body")[0]) && body.style && (div = document.createElement("div"), container = document.createElement("div"), container.style.cssText = "position:absolute;border:0;width:0;height:0;top:0;left:-9999px", body.appendChild(container).appendChild(div), void 0 !== div.style.zoom && (div.style.cssText = "display:inline;margin:0;border:0;padding:1px;width:1px;zoom:1", support.inlineBlockNeedsLayout = val = 3 === div.offsetWidth, val && (body.style.zoom = 1)), body.removeChild(container))
    }),
    function() {
        var div = document.createElement("div");
        if (null == support.deleteExpando) {
            support.deleteExpando = !0;
            try {
                delete div.test
            } catch(e) {
                support.deleteExpando = !1
            }
        }
        div = null
    } (),
    jQuery.acceptData = function(elem) {
        var noData = jQuery.noData[(elem.nodeName + " ").toLowerCase()],
        nodeType = +elem.nodeType || 1;
        return (1 === nodeType || 9 === nodeType) && (!noData || !0 !== noData && elem.getAttribute("classid") === noData)
    };
    var rbrace = /^(?:\{[\w\W]*\}|\[[\w\W]*\])$/,
    rmultiDash = /([A-Z])/g;
    jQuery.extend({
        cache: {},
        noData: {
            "applet ": !0,
            "embed ": !0,
            "object ": "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
        },
        hasData: function(elem) {
            return !! (elem = elem.nodeType ? jQuery.cache[elem[jQuery.expando]] : elem[jQuery.expando]) && !isEmptyDataObject(elem)
        },
        data: function(elem, name, data) {
            return internalData(elem, name, data)
        },
        removeData: function(elem, name) {
            return internalRemoveData(elem, name)
        },
        _data: function(elem, name, data) {
            return internalData(elem, name, data, !0)
        },
        _removeData: function(elem, name) {
            return internalRemoveData(elem, name, !0)
        }
    }),
    jQuery.fn.extend({
        data: function(key, value) {
            var i, name, data, elem = this[0],
            attrs = elem && elem.attributes;
            if (void 0 === key) {
                if (this.length && (data = jQuery.data(elem), 1 === elem.nodeType && !jQuery._data(elem, "parsedAttrs"))) {
                    for (i = attrs.length; i--;) attrs[i] && (name = attrs[i].name, 0 === name.indexOf("data-") && (name = jQuery.camelCase(name.slice(5)), dataAttr(elem, name, data[name])));
                    jQuery._data(elem, "parsedAttrs", !0)
                }
                return data
            }
            return "object" == typeof key ? this.each(function() {
                jQuery.data(this, key)
            }) : arguments.length > 1 ? this.each(function() {
                jQuery.data(this, key, value)
            }) : elem ? dataAttr(elem, key, jQuery.data(elem, key)) : void 0
        },
        removeData: function(key) {
            return this.each(function() {
                jQuery.removeData(this, key)
            })
        }
    }),
    jQuery.extend({
        queue: function(elem, type, data) {
            var queue;
            if (elem) return type = (type || "fx") + "queue",
            queue = jQuery._data(elem, type),
            data && (!queue || jQuery.isArray(data) ? queue = jQuery._data(elem, type, jQuery.makeArray(data)) : queue.push(data)),
            queue || []
        },
        dequeue: function(elem, type) {
            type = type || "fx";
            var queue = jQuery.queue(elem, type),
            startLength = queue.length,
            fn = queue.shift(),
            hooks = jQuery._queueHooks(elem, type),
            next = function() {
                jQuery.dequeue(elem, type)
            };
            "inprogress" === fn && (fn = queue.shift(), startLength--),
            fn && ("fx" === type && queue.unshift("inprogress"), delete hooks.stop, fn.call(elem, next, hooks)),
            !startLength && hooks && hooks.empty.fire()
        },
        _queueHooks: function(elem, type) {
            var key = type + "queueHooks";
            return jQuery._data(elem, key) || jQuery._data(elem, key, {
                empty: jQuery.Callbacks("once memory").add(function() {
                    jQuery._removeData(elem, type + "queue"),
                    jQuery._removeData(elem, key)
                })
            })
        }
    }),
    jQuery.fn.extend({
        queue: function(type, data) {
            var setter = 2;
            return "string" != typeof type && (data = type, type = "fx", setter--),
            arguments.length < setter ? jQuery.queue(this[0], type) : void 0 === data ? this: this.each(function() {
                var queue = jQuery.queue(this, type, data);
                jQuery._queueHooks(this, type),
                "fx" === type && "inprogress" !== queue[0] && jQuery.dequeue(this, type)
            })
        },
        dequeue: function(type) {
            return this.each(function() {
                jQuery.dequeue(this, type)
            })
        },
        clearQueue: function(type) {
            return this.queue(type || "fx", [])
        },
        promise: function(type, obj) {
            var tmp, count = 1,
            defer = jQuery.Deferred(),
            elements = this,
            i = this.length,
            resolve = function() {--count || defer.resolveWith(elements, [elements])
            };
            for ("string" != typeof type && (obj = type, type = void 0), type = type || "fx"; i--;)(tmp = jQuery._data(elements[i], type + "queueHooks")) && tmp.empty && (count++, tmp.empty.add(resolve));
            return resolve(),
            defer.promise(obj)
        }
    }),
    jQuery.event = {
        global: {},
        add: function(elem, types, handler, data, selector) {
            var tmp, events, t, handleObjIn, special, eventHandle, handleObj, handlers, type, namespaces, origType, elemData = jQuery._data(elem);
            if (elemData) {
                for (handler.handler && (handleObjIn = handler, handler = handleObjIn.handler, selector = handleObjIn.selector), handler.guid || (handler.guid = jQuery.guid++), (events = elemData.events) || (events = elemData.events = {}), (eventHandle = elemData.handle) || (eventHandle = elemData.handle = function(e) {
                    return void 0 === jQuery || e && jQuery.event.triggered === e.type ? void 0 : jQuery.event.dispatch.apply(eventHandle.elem, arguments)
                },
                eventHandle.elem = elem), types = (types || "").match(rnotwhite) || [""], t = types.length; t--;) tmp = rtypenamespace.exec(types[t]) || [],
                type = origType = tmp[1],
                namespaces = (tmp[2] || "").split(".").sort(),
                type && (special = jQuery.event.special[type] || {},
                type = (selector ? special.delegateType: special.bindType) || type, special = jQuery.event.special[type] || {},
                handleObj = jQuery.extend({
                    type: type,
                    origType: origType,
                    data: data,
                    handler: handler,
                    guid: handler.guid,
                    selector: selector,
                    needsContext: selector && jQuery.expr.match.needsContext.test(selector),
                    namespace: namespaces.join(".")
                },
                handleObjIn), (handlers = events[type]) || (handlers = events[type] = [], handlers.delegateCount = 0, special.setup && !1 !== special.setup.call(elem, data, namespaces, eventHandle) || (elem.addEventListener ? elem.addEventListener(type, eventHandle, !1) : elem.attachEvent && elem.attachEvent("on" + type, eventHandle))), special.add && (special.add.call(elem, handleObj), handleObj.handler.guid || (handleObj.handler.guid = handler.guid)), selector ? handlers.splice(handlers.delegateCount++, 0, handleObj) : handlers.push(handleObj), jQuery.event.global[type] = !0);
                elem = null
            }
        },
        remove: function(elem, types, handler, selector, mappedTypes) {
            var j, handleObj, tmp, origCount, t, events, special, handlers, type, namespaces, origType, elemData = jQuery.hasData(elem) && jQuery._data(elem);
            if (elemData && (events = elemData.events)) {
                for (types = (types || "").match(rnotwhite) || [""], t = types.length; t--;) if (tmp = rtypenamespace.exec(types[t]) || [], type = origType = tmp[1], namespaces = (tmp[2] || "").split(".").sort(), type) {
                    for (special = jQuery.event.special[type] || {},
                    type = (selector ? special.delegateType: special.bindType) || type, handlers = events[type] || [], tmp = tmp[2] && new RegExp("(^|\\.)" + namespaces.join("\\.(?:.*\\.|)") + "(\\.|$)"), origCount = j = handlers.length; j--;) handleObj = handlers[j],
                    !mappedTypes && origType !== handleObj.origType || handler && handler.guid !== handleObj.guid || tmp && !tmp.test(handleObj.namespace) || selector && selector !== handleObj.selector && ("**" !== selector || !handleObj.selector) || (handlers.splice(j, 1), handleObj.selector && handlers.delegateCount--, special.remove && special.remove.call(elem, handleObj));
                    origCount && !handlers.length && (special.teardown && !1 !== special.teardown.call(elem, namespaces, elemData.handle) || jQuery.removeEvent(elem, type, elemData.handle), delete events[type])
                } else for (type in events) jQuery.event.remove(elem, type + types[t], handler, selector, !0);
                jQuery.isEmptyObject(events) && (delete elemData.handle, jQuery._removeData(elem, "events"))
            }
        },
        trigger: function(event, data, elem, onlyHandlers) {
            var handle, ontype, cur, bubbleType, special, tmp, i, eventPath = [elem || document],
            type = hasOwn.call(event, "type") ? event.type: event,
            namespaces = hasOwn.call(event, "namespace") ? event.namespace.split(".") : [];
            if (cur = tmp = elem = elem || document, 3 !== elem.nodeType && 8 !== elem.nodeType && !rfocusMorph.test(type + jQuery.event.triggered) && (type.indexOf(".") >= 0 && (namespaces = type.split("."), type = namespaces.shift(), namespaces.sort()), ontype = type.indexOf(":") < 0 && "on" + type, event = event[jQuery.expando] ? event: new jQuery.Event(type, "object" == typeof event && event), event.isTrigger = onlyHandlers ? 2 : 3, event.namespace = namespaces.join("."), event.namespace_re = event.namespace ? new RegExp("(^|\\.)" + namespaces.join("\\.(?:.*\\.|)") + "(\\.|$)") : null, event.result = void 0, event.target || (event.target = elem), data = null == data ? [event] : jQuery.makeArray(data, [event]), special = jQuery.event.special[type] || {},
            onlyHandlers || !special.trigger || !1 !== special.trigger.apply(elem, data))) {
                if (!onlyHandlers && !special.noBubble && !jQuery.isWindow(elem)) {
                    for (bubbleType = special.delegateType || type, rfocusMorph.test(bubbleType + type) || (cur = cur.parentNode); cur; cur = cur.parentNode) eventPath.push(cur),
                    tmp = cur;
                    tmp === (elem.ownerDocument || document) && eventPath.push(tmp.defaultView || tmp.parentWindow || window)
                }
                for (i = 0; (cur = eventPath[i++]) && !event.isPropagationStopped();) event.type = i > 1 ? bubbleType: special.bindType || type,
                handle = (jQuery._data(cur, "events") || {})[event.type] && jQuery._data(cur, "handle"),
                handle && handle.apply(cur, data),
                (handle = ontype && cur[ontype]) && handle.apply && jQuery.acceptData(cur) && (event.result = handle.apply(cur, data), !1 === event.result && event.preventDefault());
                if (event.type = type, !onlyHandlers && !event.isDefaultPrevented() && (!special._default || !1 === special._default.apply(eventPath.pop(), data)) && jQuery.acceptData(elem) && ontype && elem[type] && !jQuery.isWindow(elem)) {
                    tmp = elem[ontype],
                    tmp && (elem[ontype] = null),
                    jQuery.event.triggered = type;
                    try {
                        elem[type]()
                    } catch(e) {}
                    jQuery.event.triggered = void 0,
                    tmp && (elem[ontype] = tmp)
                }
                return event.result
            }
        },
        dispatch: function(event) {
            event = jQuery.event.fix(event);
            var i, ret, handleObj, matched, j, handlerQueue = [],
            args = slice.call(arguments),
            handlers = (jQuery._data(this, "events") || {})[event.type] || [],
            special = jQuery.event.special[event.type] || {};
            if (args[0] = event, event.delegateTarget = this, !special.preDispatch || !1 !== special.preDispatch.call(this, event)) {
                for (handlerQueue = jQuery.event.handlers.call(this, event, handlers), i = 0; (matched = handlerQueue[i++]) && !event.isPropagationStopped();) for (event.currentTarget = matched.elem, j = 0; (handleObj = matched.handlers[j++]) && !event.isImmediatePropagationStopped();) event.namespace_re && !event.namespace_re.test(handleObj.namespace) || (event.handleObj = handleObj, event.data = handleObj.data, void 0 !== (ret = ((jQuery.event.special[handleObj.origType] || {}).handle || handleObj.handler).apply(matched.elem, args)) && !1 === (event.result = ret) && (event.preventDefault(), event.stopPropagation()));
                return special.postDispatch && special.postDispatch.call(this, event),
                event.result
            }
        },
        handlers: function(event, handlers) {
            var sel, handleObj, matches, i, handlerQueue = [],
            delegateCount = handlers.delegateCount,
            cur = event.target;
            if (delegateCount && cur.nodeType && (!event.button || "click" !== event.type)) for (; cur != this; cur = cur.parentNode || this) if (1 === cur.nodeType && (!0 !== cur.disabled || "click" !== event.type)) {
                for (matches = [], i = 0; i < delegateCount; i++) handleObj = handlers[i],
                sel = handleObj.selector + " ",
                void 0 === matches[sel] && (matches[sel] = handleObj.needsContext ? jQuery(sel, this).index(cur) >= 0 : jQuery.find(sel, this, null, [cur]).length),
                matches[sel] && matches.push(handleObj);
                matches.length && handlerQueue.push({
                    elem: cur,
                    handlers: matches
                })
            }
            return delegateCount < handlers.length && handlerQueue.push({
                elem: this,
                handlers: handlers.slice(delegateCount)
            }),
            handlerQueue
        },
        fix: function(event) {
            if (event[jQuery.expando]) return event;
            var i, prop, copy, type = event.type,
            originalEvent = event,
            fixHook = this.fixHooks[type];
            for (fixHook || (this.fixHooks[type] = fixHook = rmouseEvent.test(type) ? this.mouseHooks: rkeyEvent.test(type) ? this.keyHooks: {}), copy = fixHook.props ? this.props.concat(fixHook.props) : this.props, event = new jQuery.Event(originalEvent), i = copy.length; i--;) prop = copy[i],
            event[prop] = originalEvent[prop];
            return event.target || (event.target = originalEvent.srcElement || document),
            3 === event.target.nodeType && (event.target = event.target.parentNode),
            event.metaKey = !!event.metaKey,
            fixHook.filter ? fixHook.filter(event, originalEvent) : event
        },
        props: "altKey bubbles cancelable ctrlKey currentTarget eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),
        fixHooks: {},
        keyHooks: {
            props: "char charCode key keyCode".split(" "),
            filter: function(event, original) {
                return null == event.which && (event.which = null != original.charCode ? original.charCode: original.keyCode),
                event
            }
        },
        mouseHooks: {
            props: "button buttons clientX clientY fromElement offsetX offsetY pageX pageY screenX screenY toElement".split(" "),
            filter: function(event, original) {
                var body, eventDoc, doc, button = original.button,
                fromElement = original.fromElement;
                return null == event.pageX && null != original.clientX && (eventDoc = event.target.ownerDocument || document, doc = eventDoc.documentElement, body = eventDoc.body, event.pageX = original.clientX + (doc && doc.scrollLeft || body && body.scrollLeft || 0) - (doc && doc.clientLeft || body && body.clientLeft || 0), event.pageY = original.clientY + (doc && doc.scrollTop || body && body.scrollTop || 0) - (doc && doc.clientTop || body && body.clientTop || 0)),
                !event.relatedTarget && fromElement && (event.relatedTarget = fromElement === event.target ? original.toElement: fromElement),
                event.which || void 0 === button || (event.which = 1 & button ? 1 : 2 & button ? 3 : 4 & button ? 2 : 0),
                event
            }
        },
        special: {
            load: {
                noBubble: !0
            },
            focus: {
                trigger: function() {
                    if (this !== safeActiveElement() && this.focus) try {
                        return this.focus(),
                        !1
                    } catch(e) {}
                },
                delegateType: "focusin"
            },
            blur: {
                trigger: function() {
                    if (this === safeActiveElement() && this.blur) return this.blur(),
                    !1
                },
                delegateType: "focusout"
            },
            click: {
                trigger: function() {
                    if (jQuery.nodeName(this, "input") && "checkbox" === this.type && this.click) return this.click(),
                    !1
                },
                _default: function(event) {
                    return jQuery.nodeName(event.target, "a")
                }
            },
            beforeunload: {
                postDispatch: function(event) {
                    void 0 !== event.result && event.originalEvent && (event.originalEvent.returnValue = event.result)
                }
            }
        },
        simulate: function(type, elem, event, bubble) {
            var e = jQuery.extend(new jQuery.Event, event, {
                type: type,
                isSimulated: !0,
                originalEvent: {}
            });
            bubble ? jQuery.event.trigger(e, null, elem) : jQuery.event.dispatch.call(elem, e),
            e.isDefaultPrevented() && event.preventDefault()
        }
    },
    jQuery.removeEvent = document.removeEventListener ?
    function(elem, type, handle) {
        elem.removeEventListener && elem.removeEventListener(type, handle, !1)
    }: function(elem, type, handle) {
        var name = "on" + type;
        elem.detachEvent && (void 0 === elem[name] && (elem[name] = null), elem.detachEvent(name, handle))
    },
    jQuery.Event = function(src, props) {
        if (! (this instanceof jQuery.Event)) return new jQuery.Event(src, props);
        src && src.type ? (this.originalEvent = src, this.type = src.type, this.isDefaultPrevented = src.defaultPrevented || void 0 === src.defaultPrevented && !1 === src.returnValue ? returnTrue: returnFalse) : this.type = src,
        props && jQuery.extend(this, props),
        this.timeStamp = src && src.timeStamp || jQuery.now(),
        this[jQuery.expando] = !0
    };
    var rformElems = /^(?:input|select|textarea)$/i,
    rkeyEvent = /^key/,
    rmouseEvent = /^(?:mouse|pointer|contextmenu)|click/,
    rfocusMorph = /^(?:focusinfocus|focusoutblur)$/,
    rtypenamespace = /^([^.]*)(?:\.(.+)|)$/;
    jQuery.Event.prototype = {
        isDefaultPrevented: returnFalse,
        isPropagationStopped: returnFalse,
        isImmediatePropagationStopped: returnFalse,
        preventDefault: function() {
            var e = this.originalEvent;
            this.isDefaultPrevented = returnTrue,
            e && (e.preventDefault ? e.preventDefault() : e.returnValue = !1)
        },
        stopPropagation: function() {
            var e = this.originalEvent;
            this.isPropagationStopped = returnTrue,
            e && (e.stopPropagation && e.stopPropagation(), e.cancelBubble = !0)
        },
        stopImmediatePropagation: function() {
            var e = this.originalEvent;
            this.isImmediatePropagationStopped = returnTrue,
            e && e.stopImmediatePropagation && e.stopImmediatePropagation(),
            this.stopPropagation()
        }
    },
    support.submitBubbles || (jQuery.event.special.submit = {
        setup: function() {
            if (jQuery.nodeName(this, "form")) return ! 1;
            jQuery.event.add(this, "click._submit keypress._submit",
            function(e) {
                var elem = e.target,
                form = jQuery.nodeName(elem, "input") || jQuery.nodeName(elem, "button") ? elem.form: void 0;
                form && !jQuery._data(form, "submitBubbles") && (jQuery.event.add(form, "submit._submit",
                function(event) {
                    event._submit_bubble = !0
                }), jQuery._data(form, "submitBubbles", !0))
            })
        },
        postDispatch: function(event) {
            event._submit_bubble && (delete event._submit_bubble, this.parentNode && !event.isTrigger && jQuery.event.simulate("submit", this.parentNode, event, !0))
        },
        teardown: function() {
            if (jQuery.nodeName(this, "form")) return ! 1;
            jQuery.event.remove(this, "._submit")
        }
    }),
    support.changeBubbles || (jQuery.event.special.change = {
        setup: function() {
            if (rformElems.test(this.nodeName)) return "checkbox" !== this.type && "radio" !== this.type || (jQuery.event.add(this, "propertychange._change",
            function(event) {
                "checked" === event.originalEvent.propertyName && (this._just_changed = !0)
            }), jQuery.event.add(this, "click._change",
            function(event) {
                this._just_changed && !event.isTrigger && (this._just_changed = !1),
                jQuery.event.simulate("change", this, event, !0)
            })),
            !1;
            jQuery.event.add(this, "beforeactivate._change",
            function(e) {
                var elem = e.target;
                rformElems.test(elem.nodeName) && !jQuery._data(elem, "changeBubbles") && (jQuery.event.add(elem, "change._change",
                function(event) { ! this.parentNode || event.isSimulated || event.isTrigger || jQuery.event.simulate("change", this.parentNode, event, !0)
                }), jQuery._data(elem, "changeBubbles", !0))
            })
        },
        handle: function(event) {
            var elem = event.target;
            if (this !== elem || event.isSimulated || event.isTrigger || "radio" !== elem.type && "checkbox" !== elem.type) return event.handleObj.handler.apply(this, arguments)
        },
        teardown: function() {
            return jQuery.event.remove(this, "._change"),
            !rformElems.test(this.nodeName)
        }
    }),
    support.focusinBubbles || jQuery.each({
        focus: "focusin",
        blur: "focusout"
    },
    function(orig, fix) {
        var handler = function(event) {
            jQuery.event.simulate(fix, event.target, jQuery.event.fix(event), !0)
        };
        jQuery.event.special[fix] = {
            setup: function() {
                var doc = this.ownerDocument || this,
                attaches = jQuery._data(doc, fix);
                attaches || doc.addEventListener(orig, handler, !0),
                jQuery._data(doc, fix, (attaches || 0) + 1)
            },
            teardown: function() {
                var doc = this.ownerDocument || this,
                attaches = jQuery._data(doc, fix) - 1;
                attaches ? jQuery._data(doc, fix, attaches) : (doc.removeEventListener(orig, handler, !0), jQuery._removeData(doc, fix))
            }
        }
    }),
    jQuery.fn.extend({
        on: function(types, selector, data, fn, one) {
            var type, origFn;
            if ("object" == typeof types) {
                "string" != typeof selector && (data = data || selector, selector = void 0);
                for (type in types) this.on(type, selector, data, types[type], one);
                return this
            }
            if (null == data && null == fn ? (fn = selector, data = selector = void 0) : null == fn && ("string" == typeof selector ? (fn = data, data = void 0) : (fn = data, data = selector, selector = void 0)), !1 === fn) fn = returnFalse;
            else if (!fn) return this;
            return 1 === one && (origFn = fn, fn = function(event) {
                return jQuery().off(event),
                origFn.apply(this, arguments)
            },
            fn.guid = origFn.guid || (origFn.guid = jQuery.guid++)),
            this.each(function() {
                jQuery.event.add(this, types, fn, data, selector)
            })
        },
        one: function(types, selector, data, fn) {
            return this.on(types, selector, data, fn, 1)
        },
        off: function(types, selector, fn) {
            var handleObj, type;
            if (types && types.preventDefault && types.handleObj) return handleObj = types.handleObj,
            jQuery(types.delegateTarget).off(handleObj.namespace ? handleObj.origType + "." + handleObj.namespace: handleObj.origType, handleObj.selector, handleObj.handler),
            this;
            if ("object" == typeof types) {
                for (type in types) this.off(type, selector, types[type]);
                return this
            }
            return ! 1 !== selector && "function" != typeof selector || (fn = selector, selector = void 0),
            !1 === fn && (fn = returnFalse),
            this.each(function() {
                jQuery.event.remove(this, types, fn, selector)
            })
        },
        trigger: function(type, data) {
            return this.each(function() {
                jQuery.event.trigger(type, data, this)
            })
        },
        triggerHandler: function(type, data) {
            var elem = this[0];
            if (elem) return jQuery.event.trigger(type, data, elem, !0)
        }
    }),
    jQuery.fn.delay = function(time, type) {
        return time = jQuery.fx ? jQuery.fx.speeds[time] || time: time,
        type = type || "fx",
        this.queue(type,
        function(next, hooks) {
            var timeout = setTimeout(next, time);
            hooks.stop = function() {
                clearTimeout(timeout)
            }
        })
    };
    var nonce = jQuery.now(),
    rquery = /\?/;
    jQuery.parseJSON = function(data) {
        if (window.JSON && window.JSON.parse) return window.JSON.parse(data + "");
        var requireNonComma, depth = null,
        str = jQuery.trim(data + "");
        return str && !jQuery.trim(str.replace(/(,)|(\[|{)|(}|])|"(?:[^"\\\r\n]|\\["\\\/bfnrt]|\\u[\da-fA-F]{4})*"\s*:?|true|false|null|-?(?!0\d)\d+(?:\.\d+|)(?:[eE][+-]?\d+|)/g,
        function(token, comma, open, close) {
            return requireNonComma && comma && (depth = 0),
            0 === depth ? token: (requireNonComma = open || comma, depth += !close - !open, "")
        })) ? Function("return " + str)() : jQuery.error("Invalid JSON: " + data)
    },
    jQuery.parseXML = function(data) {
        var xml, tmp;
        if (!data || "string" != typeof data) return null;
        try {
            window.DOMParser ? (tmp = new DOMParser, xml = tmp.parseFromString(data, "text/xml")) : (xml = new ActiveXObject("Microsoft.XMLDOM"), xml.async = "false", xml.loadXML(data))
        } catch(e) {
            xml = void 0
        }
        return xml && xml.documentElement && !xml.getElementsByTagName("parsererror").length || jQuery.error("Invalid XML: " + data),
        xml
    };
    var ajaxLocParts, ajaxLocation, rts = /([?&])_=[^&]*/,
    rheaders = /^(.*?):[ \t]*([^\r\n]*)\r?$/gm,
    rlocalProtocol = /^(?:about|app|app-storage|.+-extension|file|res|widget):$/,
    rnoContent = /^(?:GET|HEAD)$/,
    rurl = /^([\w.+-]+:)(?:\/\/(?:[^\/?#]*@|)([^\/?#:]*)(?::(\d+)|)|)/,
    prefilters = {},
    transports = {},
    allTypes = "*/".concat("*");
    try {
        ajaxLocation = location.href
    } catch(e) {
        ajaxLocation = document.createElement("a"),
        ajaxLocation.href = "",
        ajaxLocation = ajaxLocation.href
    }
    ajaxLocParts = rurl.exec(ajaxLocation.toLowerCase()) || [],
    jQuery.extend({
        active: 0,
        lastModified: {},
        etag: {},
        ajaxSettings: {
            url: ajaxLocation,
            type: "GET",
            isLocal: rlocalProtocol.test(ajaxLocParts[1]),
            global: !0,
            processData: !0,
            async: !0,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            accepts: {
                "*": allTypes,
                text: "text/plain",
                html: "text/html",
                xml: "application/xml, text/xml",
                json: "application/json, text/javascript"
            },
            contents: {
                xml: /xml/,
                html: /html/,
                json: /json/
            },
            responseFields: {
                xml: "responseXML",
                text: "responseText",
                json: "responseJSON"
            },
            converters: {
                "* text": String,
                "text html": !0,
                "text json": jQuery.parseJSON,
                "text xml": jQuery.parseXML
            },
            flatOptions: {
                url: !0,
                context: !0
            }
        },
        ajaxSetup: function(target, settings) {
            return settings ? ajaxExtend(ajaxExtend(target, jQuery.ajaxSettings), settings) : ajaxExtend(jQuery.ajaxSettings, target)
        },
        ajaxPrefilter: addToPrefiltersOrTransports(prefilters),
        ajaxTransport: addToPrefiltersOrTransports(transports),
        ajax: function(url, options) {
            function done(status, nativeStatusText, responses, headers) {
                var isSuccess, success, error, response, modified, statusText = nativeStatusText;
                2 !== state && (state = 2, timeoutTimer && clearTimeout(timeoutTimer), transport = void 0, responseHeadersString = headers || "", jqXHR.readyState = status > 0 ? 4 : 0, isSuccess = status >= 200 && status < 300 || 304 === status, responses && (response = ajaxHandleResponses(s, jqXHR, responses)), response = ajaxConvert(s, response, jqXHR, isSuccess), isSuccess ? (s.ifModified && (modified = jqXHR.getResponseHeader("Last-Modified"), modified && (jQuery.lastModified[cacheURL] = modified), (modified = jqXHR.getResponseHeader("etag")) && (jQuery.etag[cacheURL] = modified)), 204 === status || "HEAD" === s.type ? statusText = "nocontent": 304 === status ? statusText = "notmodified": (statusText = response.state, success = response.data, error = response.error, isSuccess = !error)) : (error = statusText, !status && statusText || (statusText = "error", status < 0 && (status = 0))), jqXHR.status = status, jqXHR.statusText = (nativeStatusText || statusText) + "", isSuccess ? deferred.resolveWith(callbackContext, [success, statusText, jqXHR]) : deferred.rejectWith(callbackContext, [jqXHR, statusText, error]), jqXHR.statusCode(statusCode), statusCode = void 0, fireGlobals && globalEventContext.trigger(isSuccess ? "ajaxSuccess": "ajaxError", [jqXHR, s, isSuccess ? success: error]), completeDeferred.fireWith(callbackContext, [jqXHR, statusText]), fireGlobals && (globalEventContext.trigger("ajaxComplete", [jqXHR, s]), --jQuery.active || jQuery.event.trigger("ajaxStop")))
            }
            "object" == typeof url && (options = url, url = void 0),
            options = options || {};
            var parts, i, cacheURL, responseHeadersString, timeoutTimer, fireGlobals, transport, responseHeaders, s = jQuery.ajaxSetup({},
            options),
            callbackContext = s.context || s,
            globalEventContext = s.context && (callbackContext.nodeType || callbackContext.jquery) ? jQuery(callbackContext) : jQuery.event,
            deferred = jQuery.Deferred(),
            completeDeferred = jQuery.Callbacks("once memory"),
            statusCode = s.statusCode || {},
            requestHeaders = {},
            requestHeadersNames = {},
            state = 0,
            strAbort = "canceled",
            jqXHR = {
                readyState: 0,
                getResponseHeader: function(key) {
                    var match;
                    if (2 === state) {
                        if (!responseHeaders) for (responseHeaders = {}; match = rheaders.exec(responseHeadersString);) responseHeaders[match[1].toLowerCase()] = match[2];
                        match = responseHeaders[key.toLowerCase()]
                    }
                    return null == match ? null: match
                },
                getAllResponseHeaders: function() {
                    return 2 === state ? responseHeadersString: null
                },
                setRequestHeader: function(name, value) {
                    var lname = name.toLowerCase();
                    return state || (name = requestHeadersNames[lname] = requestHeadersNames[lname] || name, requestHeaders[name] = value),
                    this
                },
                overrideMimeType: function(type) {
                    return state || (s.mimeType = type),
                    this
                },
                statusCode: function(map) {
                    var code;
                    if (map) if (state < 2) for (code in map) statusCode[code] = [statusCode[code], map[code]];
                    else jqXHR.always(map[jqXHR.status]);
                    return this
                },
                abort: function(statusText) {
                    var finalText = statusText || strAbort;
                    return transport && transport.abort(finalText),
                    done(0, finalText),
                    this
                }
            };
            if (deferred.promise(jqXHR).complete = completeDeferred.add, jqXHR.success = jqXHR.done, jqXHR.error = jqXHR.fail, s.url = ((url || s.url || ajaxLocation) + "").replace(/#.*$/, "").replace(/^\/\//, ajaxLocParts[1] + "//"), s.type = options.method || options.type || s.method || s.type, s.dataTypes = jQuery.trim(s.dataType || "*").toLowerCase().match(rnotwhite) || [""], null == s.crossDomain && (parts = rurl.exec(s.url.toLowerCase()), s.crossDomain = !(!parts || parts[1] === ajaxLocParts[1] && parts[2] === ajaxLocParts[2] && (parts[3] || ("http:" === parts[1] ? "80": "443")) === (ajaxLocParts[3] || ("http:" === ajaxLocParts[1] ? "80": "443")))), s.data && s.processData && "string" != typeof s.data && (s.data = jQuery.param(s.data, s.traditional)), inspectPrefiltersOrTransports(prefilters, s, options, jqXHR), 2 === state) return jqXHR;
            fireGlobals = s.global,
            fireGlobals && 0 == jQuery.active++&&jQuery.event.trigger("ajaxStart"),
            s.type = s.type.toUpperCase(),
            s.hasContent = !rnoContent.test(s.type),
            cacheURL = s.url,
            s.hasContent || (s.data && (cacheURL = s.url += (rquery.test(cacheURL) ? "&": "?") + s.data, delete s.data), !1 === s.cache && (s.url = rts.test(cacheURL) ? cacheURL.replace(rts, "$1_=" + nonce++) : cacheURL + (rquery.test(cacheURL) ? "&": "?") + "_=" + nonce++)),
            s.ifModified && (jQuery.lastModified[cacheURL] && jqXHR.setRequestHeader("If-Modified-Since", jQuery.lastModified[cacheURL]), jQuery.etag[cacheURL] && jqXHR.setRequestHeader("If-None-Match", jQuery.etag[cacheURL])),
            (s.data && s.hasContent && !1 !== s.contentType || options.contentType) && jqXHR.setRequestHeader("Content-Type", s.contentType),
            jqXHR.setRequestHeader("Accept", s.dataTypes[0] && s.accepts[s.dataTypes[0]] ? s.accepts[s.dataTypes[0]] + ("*" !== s.dataTypes[0] ? ", " + allTypes + "; q=0.01": "") : s.accepts["*"]);
            for (i in s.headers) jqXHR.setRequestHeader(i, s.headers[i]);
            if (s.beforeSend && (!1 === s.beforeSend.call(callbackContext, jqXHR, s) || 2 === state)) return jqXHR.abort();
            strAbort = "abort";
            for (i in {
                success: 1,
                error: 1,
                complete: 1
            }) jqXHR[i](s[i]);
            if (transport = inspectPrefiltersOrTransports(transports, s, options, jqXHR)) {
                jqXHR.readyState = 1,
                fireGlobals && globalEventContext.trigger("ajaxSend", [jqXHR, s]),
                s.async && s.timeout > 0 && (timeoutTimer = setTimeout(function() {
                    jqXHR.abort("timeout")
                },
                s.timeout));
                try {
                    state = 1,
                    transport.send(requestHeaders, done)
                } catch(e) {
                    if (! (state < 2)) throw e;
                    done( - 1, e)
                }
            } else done( - 1, "No Transport");
            return jqXHR
        },
        getJSON: function(url, data, callback) {
            return jQuery.get(url, data, callback, "json")
        },
        getScript: function(url, callback) {
            return jQuery.get(url, void 0, callback, "script")
        }
    }),
    jQuery.each(["get", "post"],
    function(i, method) {
        jQuery[method] = function(url, data, callback, type) {
            return jQuery.isFunction(data) && (type = type || callback, callback = data, data = void 0),
            jQuery.ajax({
                url: url,
                type: method,
                dataType: type,
                data: data,
                success: callback
            })
        }
    }),
    jQuery.each(["ajaxStart", "ajaxStop", "ajaxComplete", "ajaxError", "ajaxSuccess", "ajaxSend"],
    function(i, type) {
        jQuery.fn[type] = function(fn) {
            return this.on(type, fn)
        }
    }),
    jQuery._evalUrl = function(url) {
        return jQuery.ajax({
            url: url,
            type: "GET",
            dataType: "script",
            async: !1,
            global: !1,
            throws: !0
        })
    };
    var rbracket = /\[\]$/,
    rsubmitterTypes = /^(?:submit|button|image|reset|file)$/i,
    rsubmittable = /^(?:input|select|textarea|keygen)/i;
    jQuery.param = function(a, traditional) {
        var prefix, s = [],
        add = function(key, value) {
            value = jQuery.isFunction(value) ? value() : null == value ? "": value,
            s[s.length] = encodeURIComponent(key) + "=" + encodeURIComponent(value)
        };
        if (void 0 === traditional && (traditional = jQuery.ajaxSettings && jQuery.ajaxSettings.traditional), jQuery.isArray(a) || a.jquery && !jQuery.isPlainObject(a)) jQuery.each(a,
        function() {
            add(this.name, this.value)
        });
        else for (prefix in a) buildParams(prefix, a[prefix], traditional, add);
        return s.join("&").replace(/%20/g, "+")
    },
    jQuery.fn.extend({
        serialize: function() {
            return jQuery.param(this.serializeArray())
        },
        serializeArray: function() {
            return this.map(function() {
                var elements = jQuery.prop(this, "elements");
                return elements ? jQuery.makeArray(elements) : this
            }).filter(function() {
                var type = this.type;
                return this.name && !jQuery(this).is(":disabled") && rsubmittable.test(this.nodeName) && !rsubmitterTypes.test(type) && (this.checked || !rcheckableType.test(type))
            }).map(function(i, elem) {
                var val = jQuery(this).val();
                return null == val ? null: jQuery.isArray(val) ? jQuery.map(val,
                function(val) {
                    return {
                        name: elem.name,
                        value: val.replace(/\r?\n/g, "\r\n")
                    }
                }) : {
                    name: elem.name,
                    value: val.replace(/\r?\n/g, "\r\n")
                }
            }).get()
        }
    }),
    jQuery.ajaxSettings.xhr = void 0 !== window.ActiveXObject ?
    function() {
        return ! this.isLocal && /^(get|post|head|put|delete|options)$/i.test(this.type) && createStandardXHR() || createActiveXHR()
    }: createStandardXHR;
    var xhrId = 0,
    xhrCallbacks = {},
    xhrSupported = jQuery.ajaxSettings.xhr();
    window.ActiveXObject && jQuery(window).on("unload",
    function() {
        for (var key in xhrCallbacks) xhrCallbacks[key](void 0, !0)
    }),
    support.cors = !!xhrSupported && "withCredentials" in xhrSupported,
    xhrSupported = support.ajax = !!xhrSupported,
    xhrSupported && jQuery.ajaxTransport(function(options) {
        if (!options.crossDomain || support.cors) {
            var callback;
            return {
                send: function(headers, complete) {
                    var i, xhr = options.xhr(),
                    id = ++xhrId;
                    if (xhr.open(options.type, options.url, options.async, options.username, options.password), options.xhrFields) for (i in options.xhrFields) xhr[i] = options.xhrFields[i];
                    options.mimeType && xhr.overrideMimeType && xhr.overrideMimeType(options.mimeType),
                    options.crossDomain || headers["X-Requested-With"] || (headers["X-Requested-With"] = "XMLHttpRequest");
                    for (i in headers) void 0 !== headers[i] && xhr.setRequestHeader(i, headers[i] + "");
                    xhr.send(options.hasContent && options.data || null),
                    callback = function(_, isAbort) {
                        var status, statusText, responses;
                        if (callback && (isAbort || 4 === xhr.readyState)) if (delete xhrCallbacks[id], callback = void 0, xhr.onreadystatechange = jQuery.noop, isAbort) 4 !== xhr.readyState && xhr.abort();
                        else {
                            responses = {},
                            status = xhr.status,
                            "string" == typeof xhr.responseText && (responses.text = xhr.responseText);
                            try {
                                statusText = xhr.statusText
                            } catch(e) {
                                statusText = ""
                            }
                            status || !options.isLocal || options.crossDomain ? 1223 === status && (status = 204) : status = responses.text ? 200 : 404
                        }
                        responses && complete(status, statusText, responses, xhr.getAllResponseHeaders())
                    },
                    options.async ? 4 === xhr.readyState ? setTimeout(callback) : xhr.onreadystatechange = xhrCallbacks[id] = callback: callback()
                },
                abort: function() {
                    callback && callback(void 0, !0)
                }
            }
        }
    }),
    jQuery.ajaxSetup({
        accepts: {
            script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"
        },
        contents: {
            script: /(?:java|ecma)script/
        },
        converters: {
            "text script": function(text) {
                return jQuery.globalEval(text),
                text
            }
        }
    }),
    jQuery.ajaxPrefilter("script",
    function(s) {
        void 0 === s.cache && (s.cache = !1),
        s.crossDomain && (s.type = "GET", s.global = !1)
    }),
    jQuery.ajaxTransport("script",
    function(s) {
        if (s.crossDomain) {
            var script, head = document.head || jQuery("head")[0] || document.documentElement;
            return {
                send: function(_, callback) {
                    script = document.createElement("script"),
                    script.async = !0,
                    s.scriptCharset && (script.charset = s.scriptCharset),
                    script.src = s.url,
                    script.onload = script.onreadystatechange = function(_, isAbort) { (isAbort || !script.readyState || /loaded|complete/.test(script.readyState)) && (script.onload = script.onreadystatechange = null, script.parentNode && script.parentNode.removeChild(script), script = null, isAbort || callback(200, "success"))
                    },
                    head.insertBefore(script, head.firstChild)
                },
                abort: function() {
                    script && script.onload(void 0, !0)
                }
            }
        }
    });
    var oldCallbacks = [],
    rjsonp = /(=)\?(?=&|$)|\?\?/;
    jQuery.ajaxSetup({
        jsonp: "callback",
        jsonpCallback: function() {
            var callback = oldCallbacks.pop() || jQuery.expando + "_" + nonce++;
            return this[callback] = !0,
            callback
        }
    }),
    jQuery.ajaxPrefilter("json jsonp",
    function(s, originalSettings, jqXHR) {
        var callbackName, overwritten, responseContainer, jsonProp = !1 !== s.jsonp && (rjsonp.test(s.url) ? "url": "string" == typeof s.data && !(s.contentType || "").indexOf("application/x-www-form-urlencoded") && rjsonp.test(s.data) && "data");
        if (jsonProp || "jsonp" === s.dataTypes[0]) return callbackName = s.jsonpCallback = jQuery.isFunction(s.jsonpCallback) ? s.jsonpCallback() : s.jsonpCallback,
        jsonProp ? s[jsonProp] = s[jsonProp].replace(rjsonp, "$1" + callbackName) : !1 !== s.jsonp && (s.url += (rquery.test(s.url) ? "&": "?") + s.jsonp + "=" + callbackName),
        s.converters["script json"] = function() {
            return responseContainer || jQuery.error(callbackName + " was not called"),
            responseContainer[0]
        },
        s.dataTypes[0] = "json",
        overwritten = window[callbackName],
        window[callbackName] = function() {
            responseContainer = arguments
        },
        jqXHR.always(function() {
            window[callbackName] = overwritten,
            s[callbackName] && (s.jsonpCallback = originalSettings.jsonpCallback, oldCallbacks.push(callbackName)),
            responseContainer && jQuery.isFunction(overwritten) && overwritten(responseContainer[0]),
            responseContainer = overwritten = void 0
        }),
        "script"
    }),
    jQuery.parseHTML = function(data, context, keepScripts) {
        if (!data || "string" != typeof data) return null;
        "boolean" == typeof context && (keepScripts = context, context = !1),
        context = context || document;
        var parsed = rsingleTag.exec(data),
        scripts = !keepScripts && [];
        return parsed ? [context.createElement(parsed[1])] : (parsed = jQuery.buildFragment([data], context, scripts), scripts && scripts.length && jQuery(scripts).remove(), jQuery.merge([], parsed.childNodes))
    },
    "function" == typeof define && define.amd && define("jquery", [],
    function() {
        return jQuery
    });
    var _jQuery = window.jQuery,
    _$ = window.$;
    return jQuery.noConflict = function(deep) {
        return window.$ === jQuery && (window.$ = _$),
        deep && window.jQuery === jQuery && (window.jQuery = _jQuery),
        jQuery
    },
    void 0 === noGlobal && (window.jQuery = window.$ = jQuery),
    jQuery
}),
function(G) {
    function r(d, b, c) {
        var f, m, k, e, l, p, q, t, v, h = 0,
        a = [],
        g = 0,
        w = !1,
        n = [],
        u = [],
        r = !1;
        if (c = c || {},
        f = c.encoding || "UTF8", (v = c.numRounds || 1) !== parseInt(v, 10) || 1 > v) throw Error("numRounds must a integer >= 1");
        if ("SHA-1" !== d) throw Error("Chosen SHA variant is not supported");
        l = 512,
        p = z,
        q = H,
        e = 160,
        t = function(a) {
            return a.slice()
        },
        k = A(b, f),
        m = x(d),
        this.setHMACKey = function(a, b, g) {
            var c;
            if (!0 === w) throw Error("HMAC key already set");
            if (!0 === r) throw Error("Cannot set HMAC key after calling update");
            if (f = (g || {}).encoding || "UTF8", b = A(b, f)(a), a = b.binLen, b = b.value, c = l >>> 3, g = c / 4 - 1, c < a / 8) {
                for (b = q(b, a, 0, x(d), e); b.length <= g;) b.push(0);
                b[g] &= 4294967040
            } else if (c > a / 8) {
                for (; b.length <= g;) b.push(0);
                b[g] &= 4294967040
            }
            for (a = 0; a <= g; a += 1) n[a] = 909522486 ^ b[a],
            u[a] = 1549556828 ^ b[a];
            m = p(n, m),
            h = l,
            w = !0
        },
        this.update = function(b) {
            var e, f, c, d = 0,
            q = l >>> 5;
            for (e = k(b, a, g), b = e.binLen, f = e.value, e = b >>> 5, c = 0; c < e; c += q) d + l <= b && (m = p(f.slice(c, c + q), m), d += l);
            h += d,
            a = f.slice(d >>> 5),
            g = b % l,
            r = !0;
            return function(c) {
                for (var g = "",
                d = 0; d < 5; d++) for (var f = 0; f < 4; f++) {
                    var a = c[d] >>> 8 * f;
                    a &= 255;
                    var e = Number(a).toString(16);
                    e = e.length < 2 ? "0" + e: e,
                    g += e
                }
                return g
            } (m)
        },
        this.getHash = function(b, f) {
            var c, k, l, p;
            if (!0 === w) throw Error("Cannot call getHash after setting HMAC key");
            switch (l = B(f), b) {
            case "HEX":
                c = function(a) {
                    return C(a, e, l)
                };
                break;
            case "B64":
                c = function(a) {
                    return D(a, e, l)
                };
                break;
            case "BYTES":
                c = function(a) {
                    return E(a, e)
                };
                break;
            case "ARRAYBUFFER":
                try {
                    k = new ArrayBuffer(0)
                } catch(I) {
                    throw Error("ARRAYBUFFER not supported by this environment")
                }
                c = function(a) {
                    return F(a, e)
                };
                break;
            default:
                throw Error("format must be HEX, B64, BYTES, or ARRAYBUFFER")
            }
            for (p = q(a.slice(), g, h, t(m), e), k = 1; k < v; k += 1) p = q(p, e, 0, x(d), e);
            return c(p)
        },
        this.getHMAC = function(b, f) {
            var c, k, n, r;
            if (!1 === w) throw Error("Cannot call getHMAC without first setting HMAC key");
            switch (n = B(f), b) {
            case "HEX":
                c = function(a) {
                    return C(a, e, n)
                };
                break;
            case "B64":
                c = function(a) {
                    return D(a, e, n)
                };
                break;
            case "BYTES":
                c = function(a) {
                    return E(a, e)
                };
                break;
            case "ARRAYBUFFER":
                try {
                    c = new ArrayBuffer(0)
                } catch(I) {
                    throw Error("ARRAYBUFFER not supported by this environment")
                }
                c = function(a) {
                    return F(a, e)
                };
                break;
            default:
                throw Error("outputFormat must be HEX, B64, BYTES, or ARRAYBUFFER")
            }
            return k = q(a.slice(), g, h, t(m), e),
            r = p(u, x(d)),
            r = q(k, e, l, r, e),
            c(r)
        }
    }
    function C(d, b, c) {
        var h = "";
        b /= 8;
        var a, g;
        for (a = 0; a < b; a += 1) g = d[a >>> 2] >>> 8 * (3 + a % 4 * -1),
        h += "0123456789abcdef".charAt(g >>> 4 & 15) + "0123456789abcdef".charAt(15 & g);
        return c.outputUpper ? h.toUpperCase() : h
    }
    function D(d, b, c) {
        var g, f, m, h = "",
        a = b / 8;
        for (g = 0; g < a; g += 3) for (f = g + 1 < a ? d[g + 1 >>> 2] : 0, m = g + 2 < a ? d[g + 2 >>> 2] : 0, m = (d[g >>> 2] >>> 8 * (3 + g % 4 * -1) & 255) << 16 | (f >>> 8 * (3 + (g + 1) % 4 * -1) & 255) << 8 | m >>> 8 * (3 + (g + 2) % 4 * -1) & 255, f = 0; 4 > f; f += 1) h += 8 * g + 6 * f <= b ? "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(m >>> 6 * (3 - f) & 63) : c.b64Pad;
        return h
    }
    function E(d, b) {
        var a, g, c = "",
        h = b / 8;
        for (a = 0; a < h; a += 1) g = d[a >>> 2] >>> 8 * (3 + a % 4 * -1) & 255,
        c += String.fromCharCode(g);
        return c
    }
    function F(d, b) {
        var h, c = b / 8,
        a = new ArrayBuffer(c);
        for (h = 0; h < c; h += 1) a[h] = d[h >>> 2] >>> 8 * (3 + h % 4 * -1) & 255;
        return a
    }
    function B(d) {
        var b = {
            outputUpper: !1,
            b64Pad: "=",
            shakeLen: -1
        };
        if (d = d || {},
        b.outputUpper = d.outputUpper || !1, !0 === d.hasOwnProperty("b64Pad") && (b.b64Pad = d.b64Pad), "boolean" != typeof b.outputUpper) throw Error("Invalid outputUpper formatting option");
        if ("string" != typeof b.b64Pad) throw Error("Invalid b64Pad formatting option");
        return b
    }
    function A(d, b) {
        var c;
        switch (b) {
        case "UTF8":
        case "UTF16BE":
        case "UTF16LE":
            break;
        default:
            throw Error("encoding must be UTF8, UTF16BE, or UTF16LE")
        }
        switch (d) {
        case "HEX":
            c = function(b, a, g) {
                var c, d, e, l, p, f = b.length;
                if (0 != f % 2) throw Error("String of HEX type must be in byte increments");
                for (a = a || [0], g = g || 0, p = g >>> 3, c = 0; c < f; c += 2) {
                    if (d = parseInt(b.substr(c, 2), 16), isNaN(d)) throw Error("String of HEX type contains invalid characters");
                    for (l = (c >>> 1) + p, e = l >>> 2; a.length <= e;) a.push(0);
                    a[e] |= d << 8 * (3 + l % 4 * -1)
                }
                return {
                    value: a,
                    binLen: 4 * f + g
                }
            };
            break;
        case "TEXT":
            c = function(c, a, g) {
                var f, d, e, l, p, q, t, n, k = 0;
                if (a = a || [0], g = g || 0, p = g >>> 3, "UTF8" === b) for (n = 3, e = 0; e < c.length; e += 1) for (f = c.charCodeAt(e), d = [], 128 > f ? d.push(f) : 2048 > f ? (d.push(192 | f >>> 6), d.push(128 | 63 & f)) : 55296 > f || 57344 <= f ? d.push(224 | f >>> 12, 128 | f >>> 6 & 63, 128 | 63 & f) : (e += 1, f = 65536 + ((1023 & f) << 10 | 1023 & c.charCodeAt(e)), d.push(240 | f >>> 18, 128 | f >>> 12 & 63, 128 | f >>> 6 & 63, 128 | 63 & f)), l = 0; l < d.length; l += 1) {
                    for (t = k + p, q = t >>> 2; a.length <= q;) a.push(0);
                    a[q] |= d[l] << 8 * (n + t % 4 * -1),
                    k += 1
                } else if ("UTF16BE" === b || "UTF16LE" === b) for (n = 2, e = 0; e < c.length; e += 1) {
                    for (f = c.charCodeAt(e), "UTF16LE" === b && (l = 255 & f, f = l << 8 | f >>> 8), t = k + p, q = t >>> 2; a.length <= q;) a.push(0);
                    a[q] |= f << 8 * (n + t % 4 * -1),
                    k += 2
                }
                return {
                    value: a,
                    binLen: 8 * k + g
                }
            };
            break;
        case "B64":
            c = function(b, a, c) {
                var d, k, e, l, p, q, n, f = 0;
                if ( - 1 === b.search(/^[a-zA-Z0-9=+\/]+$/)) throw Error("Invalid character in base-64 string");
                if (k = b.indexOf("="), b = b.replace(/\=/g, ""), -1 !== k && k < b.length) throw Error("Invalid '=' found in base-64 string");
                for (a = a || [0], c = c || 0, q = c >>> 3, k = 0; k < b.length; k += 4) {
                    for (p = b.substr(k, 4), e = l = 0; e < p.length; e += 1) d = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(p[e]),
                    l |= d << 18 - 6 * e;
                    for (e = 0; e < p.length - 1; e += 1) {
                        for (n = f + q, d = n >>> 2; a.length <= d;) a.push(0);
                        a[d] |= (l >>> 16 - 8 * e & 255) << 8 * (3 + n % 4 * -1),
                        f += 1
                    }
                }
                return {
                    value: a,
                    binLen: 8 * f + c
                }
            };
            break;
        case "BYTES":
            c = function(b, a, c) {
                var f, d, k, e, l;
                for (a = a || [0], c = c || 0, k = c >>> 3, d = 0; d < b.length; d += 1) f = b.charCodeAt(d),
                l = d + k,
                e = l >>> 2,
                a.length <= e && a.push(0),
                a[e] |= f << 8 * (3 + l % 4 * -1);
                return {
                    value: a,
                    binLen: 8 * b.length + c
                }
            };
            break;
        case "ARRAYBUFFER":
            try {
                c = new ArrayBuffer(0)
            } catch(h) {
                throw Error("ARRAYBUFFER not supported by this environment")
            }
            c = function(b, a, c) {
                var d, m, k, e;
                for (a = a || [0], c = c || 0, m = c >>> 3, d = 0; d < b.byteLength; d += 1) e = d + m,
                k = e >>> 2,
                a.length <= k && a.push(0),
                a[k] |= b[d] << 8 * (3 + e % 4 * -1);
                return {
                    value: a,
                    binLen: 8 * b.byteLength + c
                }
            };
            break;
        default:
            throw Error("format must be HEX, TEXT, B64, BYTES, or ARRAYBUFFER")
        }
        return c
    }
    function n(d, b) {
        return d << b | d >>> 32 - b
    }
    function u(d, b) {
        var c = (65535 & d) + (65535 & b);
        return ((d >>> 16) + (b >>> 16) + (c >>> 16) & 65535) << 16 | 65535 & c
    }
    function y(d, b, c, h, a) {
        var g = (65535 & d) + (65535 & b) + (65535 & c) + (65535 & h) + (65535 & a);
        return ((d >>> 16) + (b >>> 16) + (c >>> 16) + (h >>> 16) + (a >>> 16) + (g >>> 16) & 65535) << 16 | 65535 & g
    }
    function x(d) {
        if ("SHA-1" !== d) throw Error("No SHA variants supported");
        return [1732584193, 4023233417, 2562383102, 271733878, 3285377520]
    }
    function z(d, b) {
        var h, a, g, f, m, k, e, c = [];
        for (h = b[0], a = b[1], g = b[2], f = b[3], m = b[4], e = 0; 80 > e; e += 1) c[e] = 16 > e ? d[e] : n(c[e - 3] ^ c[e - 8] ^ c[e - 14] ^ c[e - 16], 1),
        k = 20 > e ? y(n(h, 5), a & g ^ ~a & f, m, 1518500249, c[e]) : 40 > e ? y(n(h, 5), a ^ g ^ f, m, 1859775393, c[e]) : 60 > e ? y(n(h, 5), a & g ^ a & f ^ g & f, m, 2400959708, c[e]) : y(n(h, 5), a ^ g ^ f, m, 3395469782, c[e]),
        m = f,
        f = g,
        g = n(a, 30),
        a = h,
        h = k;
        return b[0] = u(h, b[0]),
        b[1] = u(a, b[1]),
        b[2] = u(g, b[2]),
        b[3] = u(f, b[3]),
        b[4] = u(m, b[4]),
        b
    }
    function H(d, b, c, h) {
        var a;
        for (a = 15 + (b + 65 >>> 9 << 4); d.length <= a;) d.push(0);
        for (d[b >>> 5] |= 128 << 24 - b % 32, b += c, d[a] = 4294967295 & b, d[a - 1] = b / 4294967296 | 0, b = d.length, a = 0; a < b; a += 16) h = z(d.slice(a, a + 16), h);
        return h
    }
    "function" == typeof define && define.amd ? define(function() {
        return r
    }) : "undefined" != typeof exports ? ("undefined" != typeof module && module.exports && (module.exports = r), exports = r) : G.jsSHA = r
} (this),
function(global, factory) {
    "object" == typeof exports && "undefined" != typeof module ? module.exports = factory() : "function" == typeof define && (define.amd || define.cmd) ? define(factory) : global.CosCloud = factory()
} (this,
function() {
    "use strict";
    function CosCloud(opt) {
        this.appid = opt.appid,
        this.bucket = opt.bucket,
        this.region = opt.region,
        opt.getAppSign && (this.getAppSign = getEncodeFn(opt.getAppSign, this)),
        opt.getAppSignOnce && (this.getAppSignOnce = getEncodeFn(opt.getAppSignOnce, this))
    }
    function getEncodeFn(fn, context) {
        return function(callback) {
            fn.call(context,
            function(s) {
                decodeURIComponent(s) === s && (s = encodeURIComponent(s)),
                callback(s)
            })
        }
    }
    function fixPath(path, type) {
        if (!path) return "";
        var self = this;
        return path = path.replace(/(^\/*)|(\/*$)/g, ""),
        path = "folder" == type ? encodeURIComponent(path + "/").replace(/%2F/g, "/") : encodeURIComponent(path).replace(/%2F/g, "/"),
        self && (self.path = "/" + self.appid + "/" + self.bucket + "/" + path),
        path
    }
    function getSliceSHA1(opt) {
        var defer = $.Deferred(),
        sha1Algo = new window.jsSHA("SHA-1", "BYTES"),
        read = 0,
        unit = opt.sliceSize,
        reader = new FileReader,
        uploadparts = [],
        file = opt.file;
        return reader.readAsBinaryString(file.slice(read, read + unit)),
        reader.onload = function(e) {
            if (! (null == file || file.length < 1)) {
                var middle = sha1Algo.update(e.target.result),
                len = unit;
                if (len + read >= file.size ? len = file.size - read: uploadparts.push({
                    offset: read,
                    datalen: len,
                    datasha: middle
                }), (read += unit) < file.size) {
                    var end = read + unit;
                    end > file.size && (end = file.size),
                    reader.readAsBinaryString(file.slice(read, end))
                } else {
                    var sha1 = sha1Algo.getHash("HEX");
                    uploadparts.push({
                        offset: read - unit,
                        datalen: len,
                        datasha: sha1
                    }),
                    defer.resolve(uploadparts)
                }
            }
        },
        reader.onerror = function() {
            defer.reject()
        },
        defer.promise()
    }
    function sliceInit(opt) {
        var defer = $.Deferred(),
        file = opt.file,
        that = this,
        url = this.getCgiUrl(opt.path, opt.sign),
        formData = new FormData,
        uploadparts = opt.uploadparts;
        return formData.append("uploadparts", JSON.stringify(uploadparts)),
        formData.append("sha", opt.sha),
        formData.append("op", "upload_slice_init"),
        formData.append("filesize", file.size),
        formData.append("slice_size", opt.sliceSize),
        formData.append("biz_attr", opt.biz_attr),
        formData.append("insertOnly", opt.insertOnly),
        $.ajax({
            type: "POST",
            dataType: "JSON",
            url: url,
            data: formData,
            success: function(res) {
                if (res = res || {},
                0 == res.code) {
                    if (res.data.access_url) return void defer.resolve(res);
                    var session = res.data.session,
                    sliceSize = parseInt(res.data.slice_size),
                    offset = res.data.offset || 0;
                    opt.session = session,
                    opt.slice_size = sliceSize,
                    opt.offset = offset,
                    sliceUpload.call(that, opt).done(function(r) {
                        defer.resolve(r)
                    }).fail(function(r) {
                        defer.reject(r)
                    })
                } else defer.reject(res)
            },
            error: function() {
                defer.reject()
            },
            processData: !1,
            contentType: !1
        }),
        defer.promise()
    }
    function sliceUpload(opt) {
        var that = this,
        defer = $.Deferred(),
        formData = new FormData,
        file = opt.file,
        offset = opt.offset || 0,
        slice_size = opt.slice_size,
        session = opt.session,
        totalSize = file.size,
        targetOffset = offset + slice_size;
        return formData.append("sliceSize", slice_size),
        formData.append("op", "upload_slice_data"),
        formData.append("session", session),
        formData.append("offset", offset),
        opt.sha && formData.append("sha", opt.sha),
        formData.append("fileContent", that.slice.call(file, offset, targetOffset)),
        that.getAppSign(function(sign) {
            opt.sign = sign;
            var url = that.getCgiUrl(opt.path, opt.sign);
            $.ajax({
                type: "POST",
                dataType: "JSON",
                url: url,
                data: formData,
                xhr: function() {
                    var xhr = $.ajaxSettings.xhr();
                    return xhr.upload.onprogress = function(evt) {
                        var percent = (offset + evt.loaded) / file.size;
                        percent > 1 && (percent = 1),
                        "function" == typeof opt.onprogress && opt.onprogress(percent)
                    },
                    xhr
                },
                success: function(res) {
                    if (res = res || {},
                    0 == res.code) {
                        var offset = res.data.offset + slice_size;
                        offset < totalSize ? (opt.offset = offset, sliceUpload.call(that, opt).done(function(r) {
                            defer.resolve(r)
                        }).fail(function() {
                            defer.reject()
                        })) : ("function" == typeof opt.onprogress && opt.onprogress(1), defer.resolve(res))
                    } else defer.reject(res)
                },
                error: function() {
                    defer.reject()
                },
                processData: !1,
                contentType: !1
            })
        }),
        defer.promise()
    }
    function sliceList(opt) {
        var that = this,
        defer = $.Deferred(),
        file = opt.file;
        return that.getAppSign(function(sign) {
            opt.sign = sign;
            var url = that.getCgiUrl(opt.path, opt.sign),
            formData = new FormData;
            formData.append("op", "upload_slice_list"),
            $.ajax({
                type: "POST",
                dataType: "JSON",
                url: url,
                data: formData,
                success: function(res) {
                    if (res = res || {},
                    0 == res.code) {
                        opt.session = res.data.session,
                        opt.slice_size = res.data.slice_size;
                        var listparts = res.data.listparts || [];
                        opt.listparts = listparts;
                        var len = listparts.length;
                        if (len) {
                            var lastPart = opt.listparts[len - 1],
                            last_offset = lastPart.offset;
                            if (last_offset + opt.slice_size >= file.size) return defer.resolve(),
                            defer.promise();
                            opt.offset = last_offset
                        }
                        defer.resolve(res)
                    } else defer.reject(res)
                },
                error: function() {
                    defer.reject()
                },
                processData: !1,
                contentType: !1
            })
        }),
        defer.promise()
    }
    function sliceFinish(opt) {
        var that = this,
        defer = $.Deferred(),
        file = opt.file;
        return that.getAppSign(function(sign) {
            opt.sign = sign;
            var session = opt.session,
            url = that.getCgiUrl(opt.path, opt.sign),
            formData = new FormData;
            opt.sha && formData.append("sha", opt.sha),
            formData.append("op", "upload_slice_finish"),
            formData.append("filesize", file.size),
            formData.append("session", session),
            $.ajax({
                type: "POST",
                dataType: "JSON",
                url: url,
                data: formData,
                success: function(res) {
                    res = res || {},
                    0 == res.code ? defer.resolve(res) : defer.reject(res)
                },
                error: function() {
                    defer.reject()
                },
                processData: !1,
                contentType: !1
            })
        }),
        defer.promise()
    }
    return CosCloud.prototype.cosapi_cgi_url = "//REGION.file.myqcloud.com/files/v2/",
    CosCloud.prototype.slice = File.prototype.slice || File.prototype.mozSlice || File.prototype.webkitSlice,
    CosCloud.prototype.sliceSize = 3145728,
    CosCloud.prototype.getExpired = function(second) {
        return (new Date).getTime() / 1e3 + (second || 60)
    },
    CosCloud.prototype.getSliceSize = function(size) {
        return size <= 524288 ? 524288 : 1048576
    },
    CosCloud.prototype.set = function(opt) {
        opt && (this.appid = opt.appid, this.bucket = opt.bucket, this.region = opt.region, opt.getAppSign && (this.getAppSign = opt.getAppSign), opt.getAppSignOnce && (this.getAppSignOnce = opt.getAppSignOnce))
    },
    CosCloud.prototype.getCgiUrl = function(destPath, sign) {
        var region = this.region,
        bucket = this.bucket,
        url = this.cosapi_cgi_url;
        return (url = url.replace("REGION", region)) + this.appid + "/" + bucket + "/" + destPath + "?sign=" + sign
    },
    CosCloud.prototype.getAppSign = function(success, error, bucketName) {
        var expired = this.getExpired(),
        url = this.sign_url + "?sign_type=appSign&expired=" + expired + "&bucketName=" + bucketName;
        $.ajax({
            url: url,
            type: "GET",
            success: success,
            error: error
        })
    },
    CosCloud.prototype.getAppSignOnce = function(success, error, path, bucketName) {
        var url = this.sign_url + "?sign_type=appSign_once&path=" + encodeURIComponent(path) + "&bucketName=" + bucketName;
        $.ajax({
            url: url,
            type: "GET",
            success: success,
            error: error
        })
    },
    CosCloud.prototype.updateFolder = function(success, error, bucketName, remotePath, bizAttribute) {
        remotePath = fixPath.call(this, remotePath, "folder"),
        this.updateBase(success, error, bucketName, remotePath, bizAttribute)
    },
    CosCloud.prototype.updateFile = function(success, error, bucketName, remotePath, bizAttribute) {
        remotePath = fixPath.call(this, remotePath),
        this.updateBase(success, error, bucketName, remotePath, bizAttribute)
    },
    CosCloud.prototype.updateBase = function(success, error, bucketName, remotePath, bizAttribute, authority, customHeaders) {
        var that = this;
        that.getAppSignOnce(function(sign) {
            var url = that.getCgiUrl(remotePath, sign),
            formData = new FormData;
            formData.append("op", "update"),
            bizAttribute && formData.append("biz_attr", bizAttribute),
            authority && formData.append("authority", authority),
            customHeaders && (customHeaders = JSON.stringify(customHeaders), formData.append("customHeaders", customHeaders)),
            $.ajax({
                type: "POST",
                url: url,
                processData: !1,
                contentType: !1,
                data: formData,
                success: success,
                error: error
            })
        })
    },
    CosCloud.prototype.deleteFolder = function(success, error, bucketName, remotePath) {
        remotePath = fixPath.call(this, remotePath, "folder"),
        this.deleteBase(success, error, bucketName, remotePath)
    },
    CosCloud.prototype.deleteFile = function(success, error, bucketName, remotePath) {
        remotePath = fixPath.call(this, remotePath),
        this.deleteBase(success, error, bucketName, remotePath)
    },
    CosCloud.prototype.deleteBase = function(success, error, bucketName, remotePath) {
        if ("/" == remotePath) return void error({
            code: 10003,
            message: "涓嶈兘鍒犻櫎Bucket"
        });
        var that = this;
        this.getAppSignOnce(function(sign) {
            var url = that.getCgiUrl(remotePath, sign),
            formData = new FormData;
            formData.append("op", "delete"),
            $.ajax({
                type: "POST",
                url: url,
                data: formData,
                processData: !1,
                contentType: !1,
                success: success,
                error: error
            })
        })
    },
    CosCloud.prototype.getFolderStat = function(success, error, bucketName, remotePath) {
        remotePath = fixPath(remotePath, "folder"),
        this.statBase(success, error, bucketName, remotePath)
    },
    CosCloud.prototype.getFileStat = function(success, error, bucketName, remotePath) {
        remotePath = fixPath(remotePath),
        this.statBase(success, error, bucketName, remotePath)
    },
    CosCloud.prototype.statBase = function(success, error, bucketName, remotePath) {
        var that = this;
        this.getAppSign.call(that,
        function(sign) {
            var url = that.getCgiUrl(remotePath, sign),
            data = {
                op: "stat"
            };
            $.ajax({
                url: url,
                type: "GET",
                data: data,
                success: success,
                error: error
            })
        })
    },
    CosCloud.prototype.createFolder = function(success, error, bucketName, remotePath, bizAttr) {
        var that = this;
        this.getAppSign(function(sign) {
            remotePath = fixPath(remotePath, "folder");
            var url = that.getCgiUrl(remotePath, sign),
            formData = new FormData;
            formData.append("op", "create"),
            formData.append("biz_attr", bizAttr || ""),
            $.ajax({
                type: "POST",
                url: url,
                data: formData,
                processData: !1,
                contentType: !1,
                success: success,
                error: error
            })
        })
    },
    CosCloud.prototype.copyFile = function(success, error, bucketName, remotePath, destPath, overWrite) {
        var that = this;
        this.getAppSign(function(sign) {
            remotePath = fixPath(remotePath);
            var url = that.getCgiUrl(remotePath, sign),
            formData = new FormData;
            formData.append("op", "copy"),
            formData.append("dest_fileid", destPath),
            formData.append("to_over_write", overWrite),
            $.ajax({
                type: "POST",
                url: url,
                data: formData,
                processData: !1,
                contentType: !1,
                success: success,
                error: error
            })
        })
    },
    CosCloud.prototype.moveFile = function(success, error, bucketName, remotePath, destPath, overWrite) {
        var that = this;
        this.getAppSign(function(sign) {
            remotePath = fixPath(remotePath);
            var url = that.getCgiUrl(remotePath, sign),
            formData = new FormData;
            formData.append("op", "move"),
            formData.append("dest_fileid", destPath),
            formData.append("to_over_write", overWrite),
            $.ajax({
                type: "POST",
                url: url,
                data: formData,
                processData: !1,
                contentType: !1,
                success: success,
                error: error
            })
        })
    },
    CosCloud.prototype.getFolderList = function(success, error, bucketName, remotePath, num, context, order, pattern, prefix) {
        var that = this;
        remotePath = fixPath(remotePath, "folder"),
        that.listBase(success, error, bucketName, remotePath, num, context, order, pattern)
    },
    CosCloud.prototype.listBase = function(success, error, bucketName, remotePath, num, context, order, pattern, prefix) {
        var that = this;
        that.getAppSign(function(sign) {
            var url = that.getCgiUrl(remotePath, sign);
            num = num || 20,
            order = order || 0,
            pattern = pattern || "eListBoth";
            var data = {
                op: "list",
                num: num,
                context: context,
                order: order,
                pattern: pattern
            };
            $.ajax({
                url: url,
                type: "GET",
                data: data,
                success: success,
                error: error
            })
        })
    },
    CosCloud.prototype.uploadFile = function(success, error, onprogress, bucketName, remotePath, file, insertOnly) {
        var that = this;
        if (remotePath = fixPath(remotePath), file.size > 20971520) return void that.sliceUploadFile.apply(that, arguments);
        that.getAppSign(function(sign) {
            var url = that.getCgiUrl(remotePath, sign),
            formData = new FormData;
            formData.append("op", "upload"),
            formData.append("fileContent", file),
            insertOnly >= 0 && formData.append("insertOnly", insertOnly),
            $.ajax({
                type: "POST",
                url: url,
                data: formData,
                processData: !1,
                contentType: !1,
                xhr: function() {
                    var xhr = $.ajaxSettings.xhr();
                    return xhr.upload.onprogress = function(evt) {
                        var percent = evt.loaded / evt.total;
                        "function" == typeof onprogress && onprogress(percent)
                    },
                    xhr
                },
                success: success,
                error: error
            })
        })
    },
    CosCloud.prototype.sliceUploadFile = function(success, error, onprogress, bucketName, remotePath, file, insertOnly, optSliceSize, bizAttr) {
        var that = this;
        remotePath = fixPath(remotePath),
        that.getAppSign(function(sign) {
            var opt = {};
            optSliceSize = that.getSliceSize(optSliceSize),
            opt.onprogress = onprogress,
            opt.bucket = bucketName,
            opt.path = remotePath,
            opt.file = file,
            opt.insertOnly = insertOnly,
            opt.sliceSize = optSliceSize || 1048576,
            opt.appid = that.appid,
            opt.sign = sign,
            opt.biz_attr = bizAttr || "",
            sliceList.call(that, opt).always(function(res) {
                res = res || {};
                var data = res.data;
                if (data && data.session) {
                    opt.session = data.session;
                    var listparts = opt.listparts;
                    if (listparts && listparts.length) {
                        opt.listparts = listparts;
                        var len = listparts.length;
                        opt.offset = listparts[len - 1].offset
                    }
                    getSliceSHA1.call(that, opt).done(function(uploadparts) {
                        opt.uploadparts = uploadparts;
                        var len = uploadparts.length;
                        opt.sha = uploadparts[len - 1].datasha,
                        sliceUpload.call(that, opt).done(function() {
                            sliceFinish.call(that, opt).done(function(r) {
                                success(r)
                            }).fail(function(d) {
                                error({
                                    code: -1,
                                    message: d.message || "slice finish error"
                                })
                            })
                        }).fail(function(d) {
                            error({
                                code: -1,
                                message: d.message || "slice upload file error"
                            })
                        })
                    }).fail(function() {
                        error({
                            code: -1,
                            message: "get slice sha1 error"
                        })
                    })
                } else data && data.access_url ? ("function" == typeof opt.onprogress && opt.onprogress(1), success(res)) : getSliceSHA1.call(that, opt).done(function(uploadparts) {
                    opt.uploadparts = uploadparts;
                    var len = uploadparts.length;
                    opt.sha = uploadparts[len - 1].datasha,
                    sliceInit.call(that, opt).done(function(res) {
                        res = res || {};
                        var data = res.data || {};
                        data && data.access_url ? ("function" == typeof opt.onprogress && opt.onprogress(1), success(res)) : sliceFinish.call(that, opt).done(function(r) {
                            success(r)
                        }).fail(function(d) {
                            error({
                                code: -1,
                                message: d.message || "slice finish error"
                            })
                        })
                    }).fail(function(d) {
                        d = d || {},
                        error({
                            code: d.code || -1,
                            message: d.message || "upload slice file error"
                        })
                    })
                }).fail(function() {
                    error({
                        code: -1,
                        message: "get slice sha1 error"
                    })
                })
            })
        })
    },
    CosCloud
}),
function() {
    function getType(a) {
        return null === a ? "null": void 0 === a ? "undefined": Object.prototype.toString.call(a).slice(8, -1).toLowerCase()
    }
    var $ = window.jQuery.noConflict(!0);
    window.qcVideo = window.qcVideo || {},
    qcVideo.ugcUploader = qcVideo.ugcUploader || {};
    var videoTypes = ["WMV", "WM", "ASF", "ASX", "RM", "RMVB", "RA", "RAM", "MPG", "MPEG", "MPE", "VOB", "DAT", "MOV", "3GP", "MP4", "MP4V", "M4V", "MKV", "AVI", "FLV", "F4V"],
    imageTypes = ["JPG", "JPEG", "JPE", "PSD", "SVG", "SVGZ", "TIFF", "TIF", "BMP", "GIF", "PNG"],
    successCallBack = function(opts, type, applyResult, signature) {
        return function(result) {
            opts.success && opts.success({
                type: type
            }),
            applyResult && signature && finishUpload(opts, applyResult, signature)
        }
    },
    errorCallBack = function(opts, type) {
        return function(result) {
            result = result || {},
            opts.error && opts.error({
                type: type,
                msg: result.responseText
            })
        }
    },
    progressCallBack = function(opts, type) {
        return function(curr) {
            var name = "";
            name = "video" == type ? opts.videoFile.name: opts.coverFile.name,
            opts.progress && opts.progress({
                type: type,
                name: name,
                curr: curr
            })
        }
    },
    getFileMessage = function(file) {
        var fileMsg = {};
        return fileMsg.type = file.name.slice(file.name.match(/\.[^\.]+$/).index + 1),
        fileMsg.name = file.name.slice(0, file.name.match(/\.[^\.]+$/).index),
        fileMsg.size = file.size,
        fileMsg
    },
    isFunction = function(para) {
        return ! para || "function" == getType(para)
    },
    startUpload = function(opts) {
        if ("object" != getType(opts)) return "鍙傛暟蹇呴』涓哄璞＄被鍨�";
        if (!opts || !opts.videoFile || !opts.getSignature) return "闇€瑕乿ideoFile銆乬etSignature瀛楁";
        if (! (isFunction(opts.getSignature) && isFunction(opts.success) && isFunction(opts.error) && isFunction(opts.progress) && isFunction(opts.finish))) return "getSignature蹇呴』涓哄嚱鏁帮紝濡傛灉鏈塻uccess銆乪rror銆乸rogress銆乫inish锛屼篃蹇呴』涓哄嚱鏁�";
        if ("file" != getType(opts.videoFile)) return "videoFile蹇呴』涓鸿棰戞枃浠�";
        if ( - 1 == videoTypes.indexOf(getFileMessage(opts.videoFile).type.toUpperCase())) return "videoFile蹇呴』涓鸿棰戞枃浠�";
        if (opts.coverFile) {
            if ("file" != getType(opts.coverFile)) return "coverFile蹇呴』涓哄浘鐗囨枃浠�";
            if ( - 1 == imageTypes.indexOf(getFileMessage(opts.coverFile).type.toUpperCase())) return "coverFile蹇呴』涓哄浘鐗囨枃浠�"
        }
        "undefined" == getType(opts.retryStartNum) && (opts.retryStartNum = 3);
        var timeouts;
        timeouts = (opts.retryStartNum = 3) ? 3e3: (opts.retryStartNum = 2) ? 5e3: 1e4;
        var fileMessage = {};
        opts.videoFile && (fileMessage.videoMsg = getFileMessage(opts.videoFile)),
        opts.coverFile && (fileMessage.coverMsg = getFileMessage(opts.coverFile)),
        opts.getSignature(function(signature) {
            $.ajax({
                url: "https://vod2.qcloud.com/v3/index.php?Action=ApplyUploadUGC",
                data: JSON.stringify({
                    signature: signature,
                    videoName: fileMessage.videoMsg.name,
                    videoType: fileMessage.videoMsg.type,
                    videoSize: fileMessage.videoMsg.size,
                    coverName: fileMessage.coverMsg && fileMessage.coverMsg.name,
                    coverType: fileMessage.coverMsg && fileMessage.coverMsg.type,
                    coverSize: fileMessage.coverMsg && fileMessage.coverMsg.size
                }),
                type: "POST",
                dataType: "json",
                timeout: timeouts,
                success: function(result, status, xhr) {
                    if (0 == result.code) uploadFile(opts, result, signature);
                    else {
                        if (! (result.code >= 2e4 && result.code <= 29999 && opts.retryStartNum > 0)) return result.message;
                        opts.retryStartNum--,
                        startUpload(opts)
                    }
                },
                error: function(xhr, status, error) {
                    if (! (opts.retryStartNum > 0)) return error;
                    opts.retryStartNum--,
                    startUpload(opts)
                }
            })
        })
    },
    uploadFile = function(opts, result, signature) {
        if (!result.data.video) return "璇蜂笂浼犺棰戞枃浠�"; (new CosCloud({
            appid: result.data.storageAppId,
            bucket: result.data.storageBucket,
            region: result.data.storageRegion,
            getAppSign: function(callback) {
                callback(result.data.video.storageSignature)
            },
            getAppSignOnce: function(callback) {
                setTimeout(function() {
                    callback(result.data.video.storageSignature)
                },
                1e3)
            }
        }).uploadFile(successCallBack(opts, "video", result, signature), errorCallBack(opts, "video"), progressCallBack(opts, "video"), result.data.storageBucket, result.data.video.storagePath, opts.videoFile, 1), result.data.cover) ? new CosCloud({
            appid: result.data.storageAppId,
            bucket: result.data.storageBucket,
            region: result.data.storageRegion,
            getAppSign: function(callback) {
                callback(result.data.cover.storageSignature)
            },
            getAppSignOnce: function(callback) {
                setTimeout(function() {
                    callback(result.data.cover.storageSignature)
                },
                1e3)
            }
        }).uploadFile(successCallBack(opts, "cover"), errorCallBack(opts, "cover"), progressCallBack(opts, "cover"), result.data.storageBucket, result.data.cover.storagePath, opts.coverFile, 1) : console.log("鎮ㄦ病鏈変笂浼犺棰戝皝闈�")
    },
    finishUpload = function(opts, result, signature) {
        "undefined" == getType(opts.retryFinishNum) && (opts.retryFinishNum = 3);
        var timeouts;
        timeouts = (opts.retryFinishNum = 3) ? 3e3: (opts.retryFinishNum = 2) ? 5e3: 1e4,
        $.ajax({
            url: "https://vod2.qcloud.com/v3/index.php?Action=CommitUploadUGC",
            data: JSON.stringify({
                signature: signature,
                vodSessionKey: result.data.vodSessionKey
            }),
            type: "POST",
            dataType: "json",
            timeout: timeouts,
            success: function(result, status, xhr) {
                if (0 == result.code) opts.finish && opts.finish({
                    fileId: result.data.fileId,
                    videoName: opts.videoFile && opts.videoFile.name,
                    videoUrl: result.data.video && result.data.video.url,
                    coverName: opts.coverFile && opts.coverFile.name,
                    coverUrl: result.data.cover && result.data.cover.url
                });
                else {
                    if (! (result.code >= 2e4 && result.code <= 29999 && opts.retryFinishNum > 0)) return result.message;
                    opts.retryFinishNum--,
                    finishUpload(opts, result, signature)
                }
            },
            error: function(xhr, status, error) {
                if (! (opts.retryFinishNum > 0)) return error;
                opts.retryFinishNum--,
                finishUpload(opts, result, signature)
            }
        })
    };
    qcVideo.ugcUploader.start = startUpload
} ();
/*  |xGv00|152ec174ee448969d4f58d2cb05314c7 */
