package com.systems.finance.security;

import org.dataloader.DataLoaderRegistry;

public class GraphQLContext {

    private UserPrincipal userPrincipal;
    private DataLoaderRegistry dataLoaderRegistry;

    public UserPrincipal getUserPrincipal() {
        return userPrincipal;
    }

    public void setUserPrincipal(UserPrincipal userPrincipal) {
        this.userPrincipal = userPrincipal;
    }

    public DataLoaderRegistry getDataLoaderRegistry() {
        return dataLoaderRegistry;
    }

    public void setDataLoaderRegistry(DataLoaderRegistry dataLoaderRegistry) {
        this.dataLoaderRegistry = dataLoaderRegistry;
    }
}

