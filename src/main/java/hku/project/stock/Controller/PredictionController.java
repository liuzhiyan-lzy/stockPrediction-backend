package hku.project.stock.Controller;

import hku.project.stock.Service.PythonScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yann
 * @date 2024/05/02
 **/
@Controller
@RequestMapping("/stock")
public class PredictionController {
    @Autowired
    private PythonScriptService pythonScriptService;

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return pythonScriptService.runPythonScript("testPython");
    }

    @GetMapping("/prediction")
    @ResponseBody
    public String prediction(@RequestParam("symbol") String symbol) {
        return pythonScriptService.runPythonScript(symbol);
    }
}
