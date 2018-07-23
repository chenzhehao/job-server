package com.czh.springboot.mapper;

import java.util.List;
import java.util.Map;

public interface CHCRankingListMapper {

    Integer queryCHCCount();
    List<Map> queryCHCBangdan();
    List<Map> queryCHCAll();
    List<Map> queryCHCQaunGuo();
    List<Map> queryCHCSheng();


}