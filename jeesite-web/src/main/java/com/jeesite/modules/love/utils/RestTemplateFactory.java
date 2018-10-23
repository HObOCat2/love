package com.jeesite.modules.love.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestTemplateFactory {

	/**
	 * 每个ip地址上最大的链接数
	 */
	private static int defaultMaxPerRoute = 100;
	/**
	 * 连接池中最大的链接条数
	 */
	private static int maxTotle = 200;
	/**
	 * 创建连接最大时间
	 */
	private static int connectTimeout = 10 * 1000;
	/**
	 * 读取数据最大时间
	 */
	private static int readTimeout = 30 * 1000;

	private static HttpClient _client;

	static {
		/**
		 * 读取数据库或者配置文件 修改参数
		 */
		PoolingClientConnectionManager pccm = new PoolingClientConnectionManager();
		pccm.setDefaultMaxPerRoute(defaultMaxPerRoute);
		pccm.setMaxTotal(maxTotle);
		pccm.closeIdleConnections(1000, TimeUnit.SECONDS); // 链接空闲时间
		_client = new DefaultHttpClient(pccm);
		_client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeout);
		_client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 50000); // 发送数据超时时间
	}

	public static HttpClient getHttpClient() {
		return _client;
	}

	/**
	 * 返回restTemplate
	 * 
	 * @return
	 */
	public static String getResponse(HttpUriRequest post) {
		try {
			HttpResponse response = _client.execute(post);
			// System.out.println(response);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				Header ceheader = entity.getContentEncoding();
				if (ceheader != null) {
					HeaderElement[] codecs = ceheader.getElements();
					for (int i = 0; i < codecs.length; i++) {
						if (codecs[i].getName().equalsIgnoreCase("gzip")) {
							entity = new GzipDecompressingEntity(response.getEntity());
							break;
						}
					}
				}
				String charset = EntityUtils.getContentCharSet(entity) == null ? "utf-8"
						: EntityUtils.getContentCharSet(entity);
				// System.err.println("charset = " + charset);
				String body = EntityUtils.toString(entity, charset);
				// System.out.println(body);
				return body;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static HttpGet generateGet(String url, Map<String, String> headers) {
		//替换空格为%20
		url = url.replaceAll(" ", "%20");
		HttpGet get = new HttpGet(url);
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			// httpost.addHeader(entry.getKey(), entry.getValue());
			get.addHeader(new BasicHeader(entry.getKey(), entry.getValue()));
		}
		return get;
	}

	public static HttpPost postForm(String url, Map<String, String> params, Map<String, String> headers) {

		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			// httpost.addHeader(entry.getKey(), entry.getValue());
			httpost.addHeader(new BasicHeader(entry.getKey(), entry.getValue()));
		}
		Set<String> keySet = params.keySet();
		// StringBuilder sb = new StringBuilder();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
			/*
			 * sb.append(key) ; sb.append("="); sb.append(params.get(key));
			 * sb.append("\n");
			 */
		}

		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return httpost;
	}

	public static Map<String, String> genParamMap(String durgId, String fieldId) {
		Map<String, String> params = new HashMap<String, String>();

		params.put("callCount", "1");
		params.put("page", "/drug/52200.htm");
		params.put("httpSessionId", "");
		params.put("scriptSessionId", "0708C0535A789D10B43E633FEF85AC04941");
		params.put("c0-scriptName", "DrugUtils");
		params.put("c0-methodName", "showDetail");
		params.put("c0-id", "0");
		params.put("c0-param0", "number:" + durgId);
		params.put("c0-param1", "number:" + fieldId);
		params.put("batchId", "3");
		return params;
	}

	public static String getDrugProp(String durgId, String propId) {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> headers = new HashMap<String, String>();

		params.put("callCount", "1");
		params.put("page", "/drug/52200.htm");
		params.put("httpSessionId", "");
		params.put("scriptSessionId", "0708C0535A789D10B43E633FEF85AC04941");
		params.put("c0-scriptName", "DrugUtils");
		params.put("c0-methodName", "showDetail");
		params.put("c0-id", "0");
		params.put("c0-param0", "number:" + durgId);
		params.put("c0-param1", "number:" + propId);
		params.put("batchId", "3");
		/**
		 * \
		 * 
		 * Accept-Encoding:gzip,deflate Accept-Language:zh-CN,zh;q=0.8
		 * Connection:keep-alive Content-Length:207 Content-Type:text/plain
		 * Cookie:JUTE_BBS_DATA=
		 * 2bce1c34f01bdbfae9b2a448f9d93205c424748e82e017300e51e9161b48710cf0422c387b0a30e018594706235466ca7ed51deb9855d92f0d55f4cc11e7eb417c92f33cd3c448a5fdd672fda8a5739f69c6441c5d3a7a4b7fdfec4cd715f3e1;
		 * CMSSESSIONID=7FC97D3A3F21841D7F1F2B02DCBF7A07-n1;
		 * DXY_CSRF_TOKEN=5695fb34-b1a5-4221-ad81-0a670eb57e4a;
		 * JUTE_SESSION_ID=06471005-82af-47ae-92de-e23aa64f42d3;
		 * Hm_lvt_8a6dad3652ee53a288a11ca184581908=1415956985,1416190328;
		 * Hm_lpvt_8a6dad3652ee53a288a11ca184581908=1416190353;
		 * __utma=17875052.1404446053.1415956984.1416190328.1416190328.1;
		 * __utmc=17875052;
		 * __utmz=17875052.1416190328.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd
		 * =(none); DXY_USER_GROUP=53; _ga=GA1.2.1404446053.1415956984;
		 * DRUGSSESSIONID=C2D7140DF366BCF4D9D2FF4173E3B1C8-n2; __utmt=1;
		 * __utma=129582553.550443593.1415957023.1416384212.1416390140.7;
		 * __utmb=129582553.2.10.1416390140; __utmc=129582553;
		 * __utmz=129582553.1415957023.1.1.utmcsr=dxy.cn|utmccn=(referral)|
		 * utmcmd=referral|utmcct=/; __asc=3e8b85ba149c76e633252ea75f3; __auc=%
		 * 25252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525252525255Cu0025;
		 * Hm_lvt_d1780dad16c917088dd01980f5a2cfa7=1415957023,1415960129,
		 * 1416188469; Hm_lpvt_d1780dad16c917088dd01980f5a2cfa7=1416390148
		 * Host:drugs.dxy.cn Origin:http://drugs.dxy.cn
		 * Referer:http://drugs.dxy.cn/drug/2330.htm
		 * 
		 * 
		 * 
		 */
		headers.put("Cookie", "DRUGSSESSIONID=C2D7140DF366BCF4D9D2FF4173E3B1C8-n2");
		headers.put("Content-Type", "text/javascript;charset=UTF-8");
		headers.put("Referer", "http://drugs.dxy.cn/drug/52200.htm");

		headers.put("Accept-Language", "zh-cn");
		headers.put("Content-Type", "text/plain");
		headers.put("Accept-Encoding", "gzip, deflate");

		headers.put("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.101 Safari/537.36");
		String url = "http://drugs.dxy.cn/dwr/call/plaincall/DrugUtils.showDetail.dwr";
		HttpPost post = postForm(url, params, headers);
		String body = getResponse(post);
		String pb = parseString(body);
		System.out.println(pb);
		return pb;

	}

	public static String parseString(String source) {
		String abc = source.replaceAll("//#DWR-INSERT\r\n", "").replaceAll("//#DWR-REPLY\r\n", "");
		String bc = abc.substring(42, abc.length() - 5);
		return StringUtil.transUniCode2Chinese(bc);
	}

	public static HttpPost getKFPost(String url, Map<String, String> params) throws UnsupportedEncodingException {
		HttpPost httpost = new HttpPost(url);
		httpost.addHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
		httpost.addHeader(new BasicHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.101 Safari/537.36"));
		httpost.addHeader(new BasicHeader("Accept-Encoding", "gzip"));
		httpost.addHeader(new BasicHeader("charset", "utf-8"));
		httpost.addHeader(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8"));

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("ps", "88abbed20a4421e5b923e5fcaed92a44");
		map.put("at", "s");
		map.put("mk", "DFCB510D-1AE1-48F3-958C-1D118DA9AB21");
		map.put("mno", "15012345678");
		map.put("pt", "1");
		map.put("c", "0");
		map.put("or", "0");
		map.put("skey", "%");
		map.put("pg", "0");
		map.putAll(params);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, map.get(key)));
		}

		httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		return httpost;
	}

	public static HttpPost getKFDetailPost(String url, Map<String, String> params) throws UnsupportedEncodingException {
		HttpPost httpost = new HttpPost(url);
		httpost.addHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
		httpost.addHeader(new BasicHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.101 Safari/537.36"));
		httpost.addHeader(new BasicHeader("Accept-Encoding", "gzip"));
		httpost.addHeader(new BasicHeader("charset", "utf-8"));
		httpost.addHeader(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8"));

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("ps", "88abbed20a4421e5b923e5fcaed92a44");
		map.put("at", "s");
		map.put("mk", "DFCB510D-1AE1-48F3-958C-1D118DA9AB21");
		map.put("mno", "15012345678");
		// map.put("pt","1");
		// map.put("c","0");
		// map.put("or","0");
		// map.put("skey","%");
		// map.put("pg","0");
		map.put("pid", "462");
		map.putAll(params);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, map.get(key)));
		}

		httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		return httpost;
	}

	public static boolean checkWord(String content) {

		HashMap<String, String> p = new HashMap<String, String>();
		p.put("text", content);

		String result = RestTemplateFactory.getResponse(
				RestTemplateFactory.postForm("http://www.67960.com/index/check", p, new HashMap<String, String>()));

		try {
			Map<?, ?> map = new ObjectMapper().readValue(result, Map.class);
			Object o = map.get("data");
			if (o instanceof Integer) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	public static void readCloud() throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		// String url = "http://wap.xty999.com/Wap.php/API/goods_code/";
		String url = "http://wap.xty999.com/t/";
		HttpPost post = postForm(url, params, params);
		String str = getResponse(post);

		System.out.println(str.length());
		Map<?, ?> data = new ObjectMapper().readValue(str, Map.class);
		System.out.println(data.get("message"));
		List<?> list = (List<?>) data.get("result_json");

		OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream("abcabc1.txt"));
		BufferedWriter writer = new BufferedWriter(w);
		for (Object object : list) {
			Map<String, Object> prod = (Map<String, Object>) object;
			Object obj = prod.get("img");
			if (obj == null || (!obj.toString().contains(".jpg") && !obj.toString().contains(".jpeg"))) {
				System.out.println(prod.get("name"));
				System.out.println(obj.toString());
			}

			for (Map.Entry<String, Object> object2 : prod.entrySet()) {
				writer.write(object2.getKey() + ":" + object2.getValue());
				writer.newLine();
			}
			writer.newLine();
		}
		writer.close();
		System.out.println(list.size());

		// FileUtils.write(new File("woshitang.txt"), str);
	}

	public static void main(String[] args) throws Exception {
		readCloud();
		/*
		 * HashMap<String,String> map = new HashMap<String,String>();
		 * map.put("skey", "%"); map.put("pg", "149");
		 * 
		 * String url = "http://okm.kfyao.com/pl.php"; HttpPost
		 * post=getKFPost(url,map); String str = getResponse(post);
		 * System.out.println(str);
		 * 
		 * System.out.println(StringUtil.transUniCode2Chinese(str)); JsonMapper
		 * mapper = new JsonMapper(); ArrayList ll =
		 * mapper.readValue(StringUtil.transUniCode2Chinese(str),
		 * ArrayList.class); HashMap<String ,String> smap = (HashMap<String,
		 * String>) ll.get(0); System.out.println(smap.get("ID"));
		 * System.out.println(smap.get("TL"));
		 * System.out.println(smap.get("PR"));
		 * System.out.println(smap.get("MM"));
		 * System.out.println(smap.get("MS"));
		 * System.out.println(smap.get("PC1"));
		 * 
		 * HashMap<String, String> p = new HashMap<String,String>();
		 * p.put("text", "他妈的！");
		 * 
		 * String content =
		 * RestTemplateFactory.getResponse(RestTemplateFactory.postForm(
		 * "http://www.67960.com/index/check", p, new
		 * HashMap<String,String>()));
		 * 
		 * System.out.println(content);
		 */

	}

}
