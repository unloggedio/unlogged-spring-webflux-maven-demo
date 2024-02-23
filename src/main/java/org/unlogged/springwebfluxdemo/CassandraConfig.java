//package org.unlogged.springwebfluxdemo;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
//import org.springframework.data.cassandra.config.SchemaAction;
//import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
//import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
//import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
//import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//@EnableReactiveCassandraRepositories(basePackageClasses = {CassandraConfig.class})
//public class CassandraConfig extends AbstractReactiveCassandraConfiguration {
//
//    //    @Value("${cassandra.keyspace-name}")
//    String keySpace = "practice";
//
//    //    @Value("${cassandra.contact-points}")
//    String contactPoints = "127.0.0.1";
//
//    @Override
//    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
//
//        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(keySpace)
//                .ifNotExists()
//                .with(KeyspaceOption.DURABLE_WRITES, true);
//        //.withNetworkReplication(DataCenterReplication.dcr("foo", 1), DataCenterReplication.dcr("bar", 2));
//
//        return Arrays.asList(specification);
//    }
//
//    @Override
//    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
//        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(keySpace));
//    }
//
//    @Override
//    protected String getKeyspaceName() {
//        return keySpace;
//    }
//
//    @Override
//    protected String getContactPoints() {
//        return contactPoints;
//    }
//
//    @Override
//    public SchemaAction getSchemaAction() {
//        return SchemaAction.RECREATE;
//    }
//
//}