package com.swt.server.basic.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {
    private List<Entity> entityList;
    private List<Mapping> mappingList;
    //key --> servlet-name,  value --> servlet-class
    private Map<String, String> entityMap = new HashMap<>();
    //key --> url-pattern,   value --> servlet-name
    private Map<String, String> mappingMap = new HashMap<>();

    public WebContext(List<Entity> entityList, List<Mapping> mappingList) {
        this.entityList = entityList;
        this.mappingList = mappingList;

        //将entity的List转成对应的map
        for(Entity entity: entityList){
            entityMap.put(entity.getName(), entity.getClz());
        }
        //将map的List转成对应的map，会多对一
        for(Mapping mapping: mappingList){
            for(String pattern: mapping.getPatterns()){
                mappingMap.put(pattern, mapping.getName());
            }
        }

    }

    /**
     * 通过url的路径找到对应的class
     * @param pattern
     * @return
     */
    public String getClz(String pattern){
        String name = mappingMap.get(pattern);
        return entityMap.get(name);
    }

}
