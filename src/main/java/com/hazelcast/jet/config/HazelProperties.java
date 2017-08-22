package com.hazelcast.jet.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "hazelcast-jet", ignoreUnknownFields = false)
public class HazelProperties {
	
	@NotNull
	private String SP_WORKER_GET;
	
	@NotNull
	private String SP_SINGLE_WORKER_GET;
	
	@NotNull
	private String instanceName;
	
	@NotNull
	private Integer port;
	
	@NotNull
	private int QueueSize;
	
	@NotNull
	private int PacketSizeLimit;
	
	@NotNull
	private int ReceiveWindowMultiplier;
	
	@NotNull
	private int OutboxCapacity;
	
	@NotNull
	private int CooperativeThreadCount;
	
	@NotNull
	private int FlowControlPeriodMs;
	
	@NotNull
	private String TempDir;
	
	@NotNull
	private String timeReload;

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getSP_SINGLE_WORKER_GET() {
		return SP_SINGLE_WORKER_GET;
	}

	public void setSP_SINGLE_WORKER_GET(String sP_SINGLE_WORKER_GET) {
		SP_SINGLE_WORKER_GET = sP_SINGLE_WORKER_GET;
	}

	public String getSP_WORKER_GET() {
		return SP_WORKER_GET;
	}

	public void setSP_WORKER_GET(String sP_WORKER_GET) {
		SP_WORKER_GET = sP_WORKER_GET;
	}

	public int getQueueSize() {
		return QueueSize;
	}

	public void setQueueSize(int queueSize) {
		QueueSize = queueSize;
	}

	public int getPacketSizeLimit() {
		return PacketSizeLimit;
	}

	public void setPacketSizeLimit(int packetSizeLimit) {
		PacketSizeLimit = packetSizeLimit;
	}

	public int getReceiveWindowMultiplier() {
		return ReceiveWindowMultiplier;
	}

	public void setReceiveWindowMultiplier(int receiveWindowMultiplier) {
		ReceiveWindowMultiplier = receiveWindowMultiplier;
	}

	public int getOutboxCapacity() {
		return OutboxCapacity;
	}

	public void setOutboxCapacity(int outboxCapacity) {
		OutboxCapacity = outboxCapacity;
	}

	public int getCooperativeThreadCount() {
		return CooperativeThreadCount;
	}

	public void setCooperativeThreadCount(int cooperativeThreadCount) {
		CooperativeThreadCount = cooperativeThreadCount;
	}

	public int getFlowControlPeriodMs() {
		return FlowControlPeriodMs;
	}

	public void setFlowControlPeriodMs(int flowControlPeriodMs) {
		FlowControlPeriodMs = flowControlPeriodMs;
	}

	public String getTempDir() {
		return TempDir;
	}

	public void setTempDir(String tempDir) {
		TempDir = tempDir;
	}

	public String getTimeReload() {
		return timeReload;
	}

	public void setTimeReload(String timeReload) {
		this.timeReload = timeReload;
	}		

}
