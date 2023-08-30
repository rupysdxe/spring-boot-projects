package com.rupesh.multitenant_lite.main.Projection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Rupesh Dangi
 * @date: 2023/08/17 15/13
 */

public interface PersonProjection
{
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonPropertyOrder({"name"})
    interface Name
    {
        String getName();
    }

}
