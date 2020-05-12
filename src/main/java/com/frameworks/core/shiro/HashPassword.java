package com.frameworks.core.shiro;

import com.frameworks.utils.Digests;
import com.frameworks.utils.Encodes;

public class HashPassword {

	public static final int SALT_SIZE = 8;
	public static final int INTERATIONS = 1024;
	public static final String ALGORITHM = "SHA-1";
	
	private String salt;
	private String password;
	
	private static final HashPassword INSTANCE = new HashPassword();
	
	private HashPassword() {
	}
	
	public static HashPassword getMe(){
		return INSTANCE;
	}
	
	public HashPassword encryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		this.salt = Encodes.encodeHex(salt);

		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, INTERATIONS);
		this.password = Encodes.encodeHex(hashPassword);
		return this;
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @param salt 盐值
	 * @return
	 */
	public boolean validatePassword(String plainPassword, String password, String salt) {
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), Encodes.decodeHex(salt), INTERATIONS);
		String oldPassword = Encodes.encodeHex(hashPassword);
		return password.equals(oldPassword);
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
