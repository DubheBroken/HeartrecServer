package com.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.List;


public class WordFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");

        //�ڹ���������װ��ģʽ�� ԭװrequest�Ĺ�����ǿ��
        //---���غ�̨���õ�getParamter()����
        MyRequest req = new MyRequest((HttpServletRequest)request);

        chain.doFilter(req, response);//����
    }

    @Override
    public void destroy() {
    }
    
}

class MyRequest extends HttpServletRequestWrapper{
    public MyRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String str = super.getParameter(name);
        List<String> list = WordUtils.getWord();
        for(String word : list){
            str = str.replaceAll(word, "*");
        }
        return str;
    }



}