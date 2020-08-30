package com.eric.authdemo.util;

import cn.hutool.json.JSONUtil;
import com.eric.authdemo.model.common.Result;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author Eric
 */
@Slf4j
public class ResponseUtil {

    /**
     * 返回JSON
     *
     * @param response response
     * @param result   通用返回模板
     * @link com.eric.authdemo.model.common.Result
     */
    public static void out(ServletResponse response, Result<String> result) {
        // try with resource 处理机制 -> 省略finally
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json");
        try (PrintWriter out = new PrintWriter(response.getWriter())) {
            out.println(JSONUtil.toJsonStr(result));
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        }
    }
}
