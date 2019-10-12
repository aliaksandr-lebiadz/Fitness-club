package com.epam.fitness.command.factory;

import com.epam.fitness.utils.data.loader.PageDataLoader;

public interface CommandFactoryHelper {

    PageDataLoader getPageDataLoader(String pageUrl);

}