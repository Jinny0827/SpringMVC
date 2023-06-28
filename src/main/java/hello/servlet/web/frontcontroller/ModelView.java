package hello.servlet.web.frontcontroller;

import java.util.Map;


public class ModelView {

    private String viewName;
    private Map<String, Object> model;

    // viewName(경로)를 매개변수로 사용한 생성자
    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    //getter and setter 생성
    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
