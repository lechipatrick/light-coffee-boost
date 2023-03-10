package com.xgboost;

import com.fasterxml.jackson.core.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ModelParserTest {
    @Test
    void testParser() throws JsonProcessingException, URISyntaxException, FileNotFoundException {
        ModelParser parser = new ModelParser();
        URL resourceModel = this.getClass().getClassLoader().getResource("models/sample_model.json");
        assert resourceModel != null;
        File modelFile = Paths.get(resourceModel.toURI()).toFile();
        DecisionTree myTree = parser.getDecisionTreeFrom(modelFile);
        assertEquals(myTree.trees.length, 2);

        Node secondTree = myTree.trees[1];
        assertFalse(secondTree.isLeaf());
        Branch myBranch = (Branch) secondTree;
        assertEquals(myBranch.threshold(), 1, Math.ulp(0.1F));
    }

}

