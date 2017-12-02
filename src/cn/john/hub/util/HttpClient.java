package cn.john.hub.util;

import java.io.IOException;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.john.hub.domain.Proxy;

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

	private RequestConfig defaultReqCfg;

	private ResponseHandler<String> responseHandler;

	private HttpClientBuilder builder;

	private HttpRequestBase httpResq;

	public HttpClient(String url, List<Header> headers, String method) {
		this(url, headers, method, null);
	}

	public HttpClient(String url, List<Header> headers, String method, Proxy proxy) {

		builder = HttpClients.custom();

		defaultReqCfg = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000)
				.setConnectionRequestTimeout(20000).build();

		responseHandler = new ResponseHandler<String>() {
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

		if (proxy != null) {
			log.info("Building httpClient using proxy: " + proxy);
			this.proxy = proxy;
			HttpHost proxyHost = new HttpHost(proxy.getIpAddr(), Integer.parseInt(proxy.getPort()));
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxyHost);
			httpClient = builder.setDefaultRequestConfig(defaultReqCfg).setRoutePlanner(routePlanner).build();
		} else {
			log.info("Building httpClient using local ip...");
			httpClient = builder.setDefaultRequestConfig(defaultReqCfg).build();
		}

		switch (method) {
		case HubConsts.GET:
			httpResq = new HttpGet(url);
			break;
		case HubConsts.POST:
			httpResq = new HttpPost(url);
			break;
		default:
			httpResq = new HttpGet(url);
			break;
		}

		httpResq.setHeaders(headers.toArray(new Header[0]));
	}

	public String getData() {

		try {
			return httpClient.execute(httpResq, responseHandler);
		} catch (Exception e) {
			log.error("HttpClient Error!", e.getMessage());
			return null;
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
	}
	
	public Proxy getProxy(){
		return proxy;
	}

}
