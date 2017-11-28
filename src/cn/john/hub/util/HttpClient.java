package cn.john.hub.util;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.john.hub.domain.Proxy;
import cn.john.hub.spider.Queue;

/**
 * 
 * @ClassName: HttpClient
 * 
 * @Description: 请求客户端
 * 
 * @author: John
 * 
 * @date: 2017年5月22日 下午4:38:08
 * 
 * 
 */
public class HttpClient {

	private final static Logger log = LogManager.getLogger("spider");

	private Proxy proxy;
	private CloseableHttpClient httpClient;

	private RequestConfig defaultReqCfg = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000)
			.setConnectionRequestTimeout(5000).build();
	private ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
		public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity, Consts.UTF_8) : null;
			} else {
				throw new ClientProtocolException("Unexcepted response status:" + status);
			}
		}
	};

	public HttpClient() {
		httpClient = HttpClients.custom().setDefaultRequestConfig(defaultReqCfg).build();
	}

	public HttpClient(Proxy proxy) {
		this.proxy = proxy;
		HttpHost proxyHost = new HttpHost(proxy.getIpAddr(), Integer.parseInt(proxy.getPort()));
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxyHost);
		httpClient = HttpClients.custom().setDefaultRequestConfig(defaultReqCfg).setRoutePlanner(routePlanner).build();
	}

	public String getData(String url) {

		HttpGet httpResq = new HttpGet(url);

		httpResq.setHeader("User-Agent", HubConsts.USER_AGENT);
		httpResq.setHeader("Accept", HubConsts.ACCEPT);
		httpResq.setHeader("Accept-Encoding", HubConsts.ACCEPT_ENCODING);
		httpResq.setHeader("Accept-Language", HubConsts.ACCEPT_LANGUAGE);
		httpResq.setHeader("Cache-Control", HubConsts.CACHE_CONTROL);
		httpResq.setHeader("DNT", HubConsts.DNT);
		httpResq.setHeader("Accept-Charset", HubConsts.ACCEPT_CHARSET);

		try {
			String html = httpClient.execute(httpResq, responseHandler);
			if (proxy != null) {
				Queue.proxyQueue.put(proxy);
			}
			return html;
		} catch (Exception e) {
			log.error("HttpClient Error!" + e);
			return null;
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
	}

}
