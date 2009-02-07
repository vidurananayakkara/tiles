package org.apache.tiles.template;

import java.io.IOException;
import java.io.Writer;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tiles.Attribute;
import org.apache.tiles.TilesContainer;

public class GetAsStringModel {

    private Log log = LogFactory.getLog(getClass());
    
    private AttributeResolver attributeResolver;
    
    public GetAsStringModel(AttributeResolver attributeResolver) {
        this.attributeResolver = attributeResolver;
    }

    public void start(TilesContainer container, String preparer, Object... requestItems) {
        if (preparer != null) {
            container.prepare(preparer, requestItems);
        }
        container.startContext(requestItems);
    }
    
    public void end(Stack<Object> composeStack, TilesContainer container,
            Writer writer, boolean flush, boolean ignore, String preparer, String role,
            Object defaultValue, String defaultValueRole, String defaultValueType,
            String name, Attribute value, Object... requestItems) throws IOException {
        Attribute attribute = attributeResolver.computeAttribute(container,
                value, name, ignore, defaultValue, defaultValueRole,
                defaultValueType, requestItems);
        if (attribute == null && ignore) {
            return;
        }
        try {
            writer.write(attribute.getValue().toString());
        } catch (IOException e) {
            if (!ignore) {
                throw e;
            } else if (log.isDebugEnabled()) {
                log.debug("Ignoring exception", e);
            }
        } catch (RuntimeException e) {
            if (!ignore) {
                throw e;
            } else if (log.isDebugEnabled()) {
                log.debug("Ignoring exception", e);
            }
        } finally {
            container.endContext(requestItems);
        }
    }
}
