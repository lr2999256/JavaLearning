package com.learn.common.httpclient;

import javax.net.ssl.KeyManagerFactory;

public class ClientKeyStore {
	private KeyManagerFactory keyManagerFactory;
	
	ClientKeyStore(KeyManagerFactory keyManagerFactory){
		this.keyManagerFactory = keyManagerFactory;
	}
	
	KeyManagerFactory getKeyManagerFactory(){
		return keyManagerFactory;
	}
}
