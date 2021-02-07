package edu.eci.blacklistvalidator;

import edu.eci.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;

public class HostBlackListThread extends Thread{
	
	private int serverInicial;
	private int serverFinal;
	private String ipaddress;
	private HostBlacklistsDataSourceFacade fachada;
	private LinkedList<Integer> hostblackList;
	private int hostblackListCount = 0;
	private int hostblackListCheked = 0;
	
	private static final int BLACK_LIST_ALARM_COUNT=5;
	
	public HostBlackListThread(int serverInicial, int serverFinal, String ipaddress, HostBlacklistsDataSourceFacade fachada){
		
		this.serverFinal = serverFinal;
		this.serverInicial = serverInicial;
		this.ipaddress = ipaddress;
		this.fachada = fachada;
		hostblackList = new LinkedList<>();
		
	}
	
	public void run(){
		
		for(int i = serverInicial; i < serverFinal && getHostblackListCount() < BLACK_LIST_ALARM_COUNT; i++){
			hostblackListCheked += 1;
			if(fachada.isInBlackListServer(i, ipaddress)){
				hostblackList.add(i);
				hostblackListCount += 1;
			}
			
		}
		
	}
	
	public int getHostblackListCount(){
		return this.hostblackListCount;
	}
	
	public int getHostblackListCheked(){
		return this.hostblackListCheked;
	}
	
	public LinkedList<Integer> getHostblackList(){
		return this.hostblackList;
	}
	
}