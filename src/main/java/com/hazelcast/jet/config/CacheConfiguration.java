package com.hazelcast.jet.config;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.config.EdgeConfig;
import com.hazelcast.jet.config.InstanceConfig;
import com.hazelcast.jet.config.JetConfig;

@Configuration
public class CacheConfiguration {

    private final Logger log = LoggerFactory.getLogger(CacheConfiguration.class);
    
    private final Environment env;
    
    @Autowired
	HazelProperties applicationProperties;
    
    public CacheConfiguration(Environment env) {
        this.env = env;
    }
    
    @Bean
    public JetInstance jetInstance(Environment env){    	    	    	
    	JetConfig config = new JetConfig();
    	
    	Config configHZ = new Config();
    	configHZ.setInstanceName(applicationProperties.getInstanceName());
    	configHZ.getNetworkConfig().setPort(applicationProperties.getPort());
    	configHZ.getNetworkConfig().setPortAutoIncrement(true);

      // In development, remove multicast auto-configuration
     // if (env.acceptsProfiles(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)) {
       //   System.setProperty("hazelcast.local.localAddress", "127.0.0.1");
//
  //        configHZ.getNetworkConfig().getJoin().getAwsConfig().setEnabled(false);
    //      configHZ.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
      //    configHZ.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(false);
      //}
      configHZ.getMapConfigs().put("default", initializeDefaultMapConfig());
    	
    	config.setDefaultEdgeConfig(initializeEdgeconfig());
    	config.setInstanceConfig(initializeInstanceConfig());
    	config.setHazelcastConfig(configHZ);
    	return Jet.newJetInstance(config);
    }
    
    @PreDestroy
    public void destroy() {
        log.info("Closing Jet Cache");
        Jet.shutdownAll();
    }
    
    public EdgeConfig initializeEdgeconfig (){
    	EdgeConfig edgeConfig = new EdgeConfig();
    	edgeConfig.setQueueSize(applicationProperties.getQueueSize());
    	edgeConfig.setPacketSizeLimit(applicationProperties.getPacketSizeLimit());
    	edgeConfig.setReceiveWindowMultiplier(applicationProperties.getReceiveWindowMultiplier());
    	edgeConfig.setOutboxCapacity(applicationProperties.getOutboxCapacity());
    	return edgeConfig;
    }
    
    public InstanceConfig initializeInstanceConfig (){
    	InstanceConfig instanceConfig = new InstanceConfig();
    	instanceConfig.setCooperativeThreadCount(applicationProperties.getCooperativeThreadCount());
    	instanceConfig.setFlowControlPeriodMs(applicationProperties.getFlowControlPeriodMs());
    	instanceConfig.setTempDir(applicationProperties.getTempDir());
    	return instanceConfig;
    }

    private MapConfig initializeDefaultMapConfig() {
        MapConfig mapConfig = new MapConfig();

    /*
        Number of backups. If 1 is set as the backup-count for example,
        then all entries of the map will be copied to another JVM for
        fail-safety. Valid numbers are 0 (no backup), 1, 2, 3.
     */
        mapConfig.setBackupCount(0);

    /*
        Valid values are:
        NONE (no eviction),
        LRU (Least Recently Used),
        LFU (Least Frequently Used).
        NONE is the default.
     */
        mapConfig.setEvictionPolicy(EvictionPolicy.LRU);

    /*
        Maximum size of the map. When max size is reached,
        map is evicted based on the policy defined.
        Any integer between 0 and Integer.MAX_VALUE. 0 means
        Integer.MAX_VALUE. Default is 0.
     */
        mapConfig.setMaxSizeConfig(new MaxSizeConfig(0, MaxSizeConfig.MaxSizePolicy.USED_HEAP_SIZE));

        return mapConfig;
    }

}
