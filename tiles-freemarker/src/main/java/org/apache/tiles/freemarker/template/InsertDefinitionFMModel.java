package org.apache.tiles.freemarker.template;

import java.io.IOException;
import java.util.Map;

import org.apache.tiles.freemarker.context.FreeMarkerUtil;
import org.apache.tiles.template.InsertDefinitionModel;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class InsertDefinitionFMModel implements TemplateDirectiveModel {

    private InsertDefinitionModel model;

    public InsertDefinitionFMModel(InsertDefinitionModel model) {
        this.model = model;
    }

    @SuppressWarnings("unchecked")
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException {
        Map<String, TemplateModel> parms = (Map<String, TemplateModel>) params;
        model.start(FreeMarkerUtil.getCurrentContainer(env), env);
        FreeMarkerUtil.evaluateBody(body);
        model.end(FreeMarkerUtil.getCurrentContainer(env), FreeMarkerUtil
                .getAsString(parms.get("name")), FreeMarkerUtil
                .getAsString(parms.get("template")), FreeMarkerUtil
                .getAsString(parms.get("role")), FreeMarkerUtil
                .getAsString(parms.get("preparer")), env);
    }

}
