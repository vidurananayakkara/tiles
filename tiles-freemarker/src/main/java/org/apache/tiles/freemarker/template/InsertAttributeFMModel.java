package org.apache.tiles.freemarker.template;

import java.io.IOException;
import java.util.Map;

import org.apache.tiles.Attribute;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.freemarker.context.FreeMarkerUtil;
import org.apache.tiles.template.InsertAttributeModel;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class InsertAttributeFMModel implements TemplateDirectiveModel {

    private InsertAttributeModel model;
    
    public InsertAttributeFMModel(InsertAttributeModel model) {
        this.model = model;
    }

    @SuppressWarnings("unchecked")
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException {
        Map<String, TemplateModel> parms = (Map<String, TemplateModel>) params;
        TilesContainer container = FreeMarkerUtil.getCurrentContainer(env);
        model.start(container, FreeMarkerUtil.getAsString(parms.get("preparer")), env);
        FreeMarkerUtil.evaluateBody(body);
        model.end(FreeMarkerUtil.getComposeStack(env), container,
                FreeMarkerUtil.getAsBoolean(parms.get("flush"), false),
                FreeMarkerUtil.getAsBoolean(parms.get("ignore"), false),
                FreeMarkerUtil.getAsString(parms.get("preparer")),
                FreeMarkerUtil.getAsString(parms.get("role")),
                FreeMarkerUtil.getAsObject(parms.get("defaultValue")),
                FreeMarkerUtil.getAsString(parms.get("defaultValueRole")),
                FreeMarkerUtil.getAsString(parms.get("defaultValueType")),
                FreeMarkerUtil.getAsString(parms.get("name")),
                (Attribute) FreeMarkerUtil.getAsObject(parms.get("value")), env);
    }

}
