package com.github.viniciusfcf;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@DBRider
@QuarkusTest
@QuarkusTestResource(DatabaseLifecycle.class)
public class ProdutoTest {

    @Test
    @DataSet(value = "produtos1.yml", cleanBefore = true, disableConstraints = true)
    public void testUm() {
        Assert.assertEquals(1, Produto.count());
    }

}