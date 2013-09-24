package cn.com.kxcomm.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * 功能描述:动态读取配置文件来加载属性
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author dengcd 新增日期：2008-10-9
 * @author 你的姓名 修改日期：2008-10-9
 * @since wapportal_manager version(2.0)
 */
public class PropertyReader {

	private static Logger _log = Logger.getLogger(PropertyReader.class);

	private static Hashtable<String, Properties> pptContainer = new Hashtable<String, Properties>();

	/**
	 * 
	 * 方法用途和描述: 采用绝对文件路径，直接从目标文件中获取对应key的值
	 * 
	 * @param propertyFilePath
	 *            绝对文件路径
	 * @param key
	 * @return
	 * @author dengcd 新增日期：2010-1-14
	 * @author 你的姓名 修改日期：2010-1-14
	 * @since wapportal_manager version(2.0)
	 */
	public final static String getCurrentValue(String propertyFilePath,
			String key) {
		String value = "";
		try {
			InputStream fis = new java.io.FileInputStream(propertyFilePath);
			Properties ppts = new Properties();
			ppts.load(fis);
			value = ppts.getProperty(key);
			fis.close();
		} catch (java.io.FileNotFoundException e) {
			_log.error("FileInputStream(\"" + propertyFilePath
					+ "\")! FileNotFoundException: " + e);
			return null;
		} catch (java.io.IOException e) {
			_log.error("Properties.load(InputStream)! IOException: " + e);
			return null;
		}
		return value;
	}

	/**
	 * 
	 * 方法用途和描述: 获得属性
	 * 
	 * @param propertyFilePath
	 *            属性文件(包括类路径)
	 * @param key
	 *            属性键
	 * @return 属性值
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static String getValue(String propertyFilePath, String key) {
		Properties ppts = getProperties(propertyFilePath);
		return ppts == null ? null : ppts.getProperty(key);
	}

	/**
	 * 
	 * 方法用途和描述: 获得属性文件中Key所对应的值
	 * 
	 * @param propertyFilePath
	 *            属性文件路径(包括类路径或文件系统中文件路径)
	 * @param key
	 *            属性的键
	 * @param isAbsolutePath
	 *            是否为绝对路径:true|false〔即是本地文件系统路径，比如：C:/test.propreties〕<br>
	 * <br>
	 *            <b>注：</b>不能通过类路径来获取到属性文件，而只知道属性文件的文件系统路径，即文件系统地址则用此方法来获取其中的Key所对应的Value
	 * @return key的属性值
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static String getValue(String propertyFilePath, String key,
			boolean isAbsolutePath) {
		if (isAbsolutePath) {
			Properties ppts = getPropertiesByFs(propertyFilePath);
			return ppts == null ? null : ppts.getProperty(key);
		}
		return getValue(propertyFilePath, key);
	}

	/**
	 * 
	 * 方法用途和描述: 获得属性文件的属性
	 * 
	 * @param propertyFilePath
	 *            属性文件(包括类路径)
	 * @return 属性
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static Properties getProperties(String propertyFilePath) {
		if (propertyFilePath == null) {
			_log.error("propertyFilePath is null!");
			return null;
		}
		Properties ppts = pptContainer.get(propertyFilePath);
		if (ppts == null) {
			ppts = loadPropertyFile(propertyFilePath);
			if (ppts != null) {
				pptContainer.put(propertyFilePath, ppts);
			}
		}
		return ppts;
	}

	/**
	 * 
	 * 方法用途和描述: 获得属性文件的属性
	 * 
	 * @param propertyFilePath
	 *            属性文件路径(包括类路径及文件系统路径)
	 * @return 属性文件对象 Properties
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static Properties getPropertiesByFs(String propertyFilePath) {
		if (propertyFilePath == null) {
			_log.error("propertyFilePath is null!");
			return null;
		}
		Properties ppts = pptContainer.get(propertyFilePath);
		if (ppts == null) {
			ppts = loadPropertyFileByFileSystem(propertyFilePath);
			if (ppts != null) {
				pptContainer.put(propertyFilePath, ppts);
			}
		}
		return ppts;
	}

	/**
	 * 
	 * 方法用途和描述: 加载属性
	 * 
	 * @param propertyFilePath
	 *            属性文件(包括类路径)
	 * @return 属性
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	private static Properties loadPropertyFile(String propertyFilePath) {
		_log.info("loadPropertyFile:" + propertyFilePath);
		java.io.InputStream is = PropertyReader.class
				.getResourceAsStream(propertyFilePath);
		if (is == null) {
			_log.info("loadPropertyFile---is null:");
			return loadPropertyFileByFileSystem(propertyFilePath);
		}
		Properties ppts = new Properties();
		try {
			_log.info("loadPropertyFile---is load:");
			ppts.load(is);
			is.close();
			return ppts;
		} catch (Exception e) {
			_log.debug("加载属性文件出错:" + propertyFilePath, e);
			return null;
		}
	}

	/**
	 * 
	 * 方法用途和描述: 从文件系统加载属性文件
	 * 
	 * @param propertyFilePath
	 *            属性文件(文件系统的文件路径)
	 * @return 属性
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	private static Properties loadPropertyFileByFileSystem(
			final String propertyFilePath) {
		try {
			InputStream fis = new FileInputStream(propertyFilePath);
			Properties ppts = new Properties();
			ppts.load(fis);
			fis.close();
			return ppts;
		} catch (java.io.FileNotFoundException e) {
			_log.error("FileInputStream(\"" + propertyFilePath
					+ "\")! FileNotFoundException: " + e);
			return null;
		} catch (java.io.IOException e) {
			_log.error("Properties.load(InputStream)! IOException: " + e);
			return null;
		}
	}

	/**
	 * 
	 * 方法用途和描述: 对存在的属性文件中添加键值对并保存
	 * 
	 * @param propertyFilePath
	 *            属性文件的路径(包括类路径及文件系统路径)
	 * @param htKeyValue
	 *            键值对Hashtable
	 * @return
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static boolean setValueAndStore(String propertyFilePath,
			java.util.Hashtable<String, String> htKeyValue) {
		return setValueAndStore(propertyFilePath, htKeyValue, null);
	}

	/**
	 * 
	 * 方法用途和描述: 对存在的属性文件中添加键值对并保存
	 * 
	 * @param propertyFilePath
	 *            属性文件的路径(包括类路径及文件系统路径)
	 * @param htKeyValue
	 *            键值对Hashtable
	 * @param storeMsg
	 *            保存时添加的附加信息（注释）
	 * @return
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static boolean setValueAndStore(String propertyFilePath,
			java.util.Hashtable<String, String> htKeyValue, String storeMsg) {
		Properties ppts = getProperties(propertyFilePath);

		if (ppts == null || htKeyValue == null) {
			return false;
		}
		ppts.putAll(htKeyValue);
		java.io.OutputStream stream = null;
		try {
			stream = new java.io.FileOutputStream(propertyFilePath);
		} catch (FileNotFoundException e) {
			_log.debug("propertyFilePath = " + propertyFilePath);
			String path = PropertyReader.class.getResource(propertyFilePath)
					.getPath();
			_log.debug("~~~~~~~~path~~~XXX~~~~~" + path);
			try {
				stream = new java.io.FileOutputStream(path);
			} catch (FileNotFoundException e1) {
				_log.error("FileNotFoundException! path=" + propertyFilePath);
				return false;
			}
		}

		if (stream == null) {
			return false;
		}

		try {
			ppts.store(stream, storeMsg != null ? storeMsg
					: "set value and store.");
			return true;
		} catch (java.io.IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * 方法用途和描述: 创建属性文件
	 * 
	 * @param propertyFilePath
	 *            要存储属性文件的路径
	 * @param htKeyValue
	 *            属性文件中的键值对Hashtable
	 * @return
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static boolean createPropertiesFile(String propertyFilePath,
			java.util.Hashtable<String, String> htKeyValue) {
		java.io.File file = new java.io.File(propertyFilePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
		}
		return setValueAndStore(propertyFilePath, htKeyValue,
				"create properties file:" + file.getName());
	}

	/**
	 * 
	 * 方法用途和描述:设置属性值
	 * 
	 * @param propertyFilePath
	 *            属性文件(包括类路径)
	 * @param key
	 *            属性键
	 * @param value
	 *            属性值
	 * @return
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static boolean setValue(String propertyFilePath, String key,
			String value) {
		Properties ppts = getProperties(propertyFilePath);
		if (ppts == null) {
			return false;
		}
		ppts.put(key, value);
		return true;
	}

	/**
	 * 
	 * 方法用途和描述: 保存属性文件对象
	 * 
	 * @param properties
	 *            属性文件对象
	 * @param propertyFilePath
	 *            要保存的路径
	 * @param msg
	 *            保存时添加的附加信息（注释）
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static void store(Properties properties,
			String propertyFilePath, String msg) {
		try {
			java.io.OutputStream stream = new java.io.FileOutputStream(
					propertyFilePath);
			properties.store(stream, msg);
			stream.close();
		} catch (java.io.FileNotFoundException e) {
			_log.error("FileOutputStream(" + propertyFilePath
					+ ")! FileNotFoundException: " + e);
		} catch (java.io.IOException e) {
			_log.error("store(stream, msg)! IOException: " + e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 方法用途和描述: 删除属性值
	 * 
	 * @param propertyFilePath
	 *            属性文件(包括类路径)
	 * @param key
	 *            属性键
	 * @return
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static String removeValue(String propertyFilePath, String key) {

		Properties ppts = getProperties(propertyFilePath);
		if (ppts == null) {
			return null;
		}
		return (String) ppts.remove(key);
	}

	/**
	 * 
	 * 方法用途和描述: 删除属性文件中的Key数组所对应的键值对
	 * 
	 * @param propertyFilePath
	 *            属性文件路径(包括类路径及文件系统路径)
	 * @param key
	 *            key数组
	 * @return 属性文件对象
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static Properties removeValue(String propertyFilePath,
			String[] key) {
		if (key == null) {
			_log.error("key[] is null!");
			return null;
		}
		Properties ppts = getProperties(propertyFilePath);
		if (ppts == null) {
			return null;
		}
		for (String strKey : key) {
			ppts.remove(strKey);
		}
		return ppts;
	}

	/**
	 * 
	 * 方法用途和描述:删除属性文件中的Key数组所对应的键值对，并将属性文件对象持久化（即保存）
	 * 
	 * 
	 * @param propertyFilePath
	 *            属性文件路径(包括类路径及文件系统路径)
	 * @param key
	 *            属性文件中的key数组
	 * @return 成功与否（true|false）
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static boolean removeValueAndStore(String propertyFilePath,
			String[] key) {
		Properties ppts = removeValue(propertyFilePath, key);
		if (ppts == null) {
			return false;
		}
		store(ppts, propertyFilePath, "batch remove key value!");
		return true;
	}

	/**
	 * 
	 * 方法用途和描述: 更新指定路径的属性文件中的键所对应的值
	 * 
	 * @param propertyFilePath
	 *            属性文件路径(包括类路径及文件系统路径)
	 * @param key
	 *            属性文件中的key
	 * @param newValue
	 *            要更新的新值
	 * @return 成功与否（true|false）
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static boolean updateValue(String propertyFilePath,
			String key, String newValue) {
		if (key == null || newValue == null) {
			_log.error("key or newValue is null!");
			return false;
		}
		java.util.Hashtable<String, String> ht = new java.util.Hashtable<String, String>();
		ht.put(key, newValue);
		return setValueAndStore(propertyFilePath, ht, "update " + key
				+ "'s value!");
	}

	/**
	 * 
	 * 方法用途和描述: 批量更新指定路径的属性文件中的键所对应的值
	 * 
	 * @param propertyFilePath
	 *            属性文件路径(包括类路径及文件系统路径)
	 * @param htKeyValue
	 *            要更新的键值对Hashtable
	 * @return 成功与否（true|false）
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static boolean batchUpdateValue(String propertyFilePath,
			java.util.Hashtable<String, String> htKeyValue) {
		if (propertyFilePath == null || htKeyValue == null) {
			return false;
		}
		return setValueAndStore(propertyFilePath, htKeyValue,
				"batch update key value!");
	}

	/**
	 * 
	 * 方法用途和描述: 移除加载的属性文件
	 * 
	 * @param propertyFilePath
	 *            属性文件(包括类路径)
	 * @return
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static Properties removePropertyFile(String propertyFilePath) {

		return pptContainer.remove(propertyFilePath);
	}

	/**
	 * 
	 * 方法用途和描述: 重新加载某个Property文件
	 * 
	 * @param propertyFilePath
	 *            要重新加载的Property文件，如果当前内存中没有的话则加载，否则替换
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static void reloadPropertyFile(String propertyFilePath) {
		pptContainer.remove(propertyFilePath);
		loadPropertyFile(propertyFilePath);
	}

	/**
	 * 
	 * 方法用途和描述: 获得属性文件的路径
	 * 
	 * @param pkg
	 *            包名
	 * @param propertyFileName
	 *            属性文件名
	 * @return
	 * @author dengcd 新增日期：2008-10-9
	 * @author 你的姓名 修改日期：2008-10-9
	 * @since wapportal_manager version(2.0)
	 */
	public final static String getPpropertyFilePath(String pkg,
			String propertyFileName) {

		pkg = pkg == null ? "" : pkg.replaceAll("\\.", "/");
		propertyFileName = propertyFileName.endsWith(".properties") ? propertyFileName
				: (propertyFileName + ".properties");
		return "/" + pkg + "/" + propertyFileName;
	}

	public static void main(String[] args) {
		// String path = "D:/download/abc.properties";
		String path = "/config/commonConfig.properties";
		// String v = PropertyReader.getValue(path, "parentIP");
		// _log.info("value0 = " + v);
		// Hashtable<String, String> ht = new Hashtable<String, String>();
		// ht.put("name", "dengcd");
		// PropertyReader.setValueAndStore(path, ht);
		String v_ = PropertyReader.getValue(path, "filepath");
		_log.info("value1 = " + v_ + path);
		// PropertyReader.reloadPropertyFile(v_+path);
		String v2_ = PropertyReader.getValue(v_, "filepath", true);
		_log.info("value2 = " + v2_);

		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PropertyReader.reloadPropertyFile(path);
		String v3_ = PropertyReader.getValue(path, "filepath");
		_log.info("value3 = " + v3_);
	}

}