package com.example.tommy.lib;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
public class CrawlerlUtil {
    private static String agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36"
            + " (KHTML, like Gecko) Chrome/74.0.3724.8 Safari/537.36";
    public static void main(String[] args) {
    }
    public static List<Poem> search(String type, String... keywords) {
        List<Poem> lists = new ArrayList<Poem>();
        int count = 1;
        String temp = "";
        for (int i = 0; i < keywords.length; i++) {
            if (i < keywords.length - 1) {
                temp = temp + keywords[i] + "+";
            } else {
                temp = temp + keywords[i];
            }
        }
        String str = "";
        try {
            for (; count <= 1; count++) {
                str = "https://so.gushiwen.org/search.aspx?page=" + count + "&type=" + type + "&value=" + temp;
                Document doucment = Jsoup.connect(str).userAgent(agent).timeout(10000).get();
                Elements eles = doucment.select(".cont");
                for (Element ele : eles) {
                    for (Element sss : ele.select(".source").prev()) {
                        Poem poem = new Poem(sss.text(), "https://so.gushiwen.org" + sss.select("a").attr("href"));
                        lists.add(poem);
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lists;
    }
    public static void poems(Poem poem) {
        try {
            Document document = Jsoup.connect(poem.url).userAgent(agent).timeout(10000).get();
            Element title = document.selectFirst(".cont h1");
            poem.title = title.text();
            Element author = document.selectFirst(".cont .source");
            poem.author = author.text();
            if (document.selectFirst(".contson:has(p)") != null
                    && !document.selectFirst(".contson").select("p").text().equals("")) {
                Elements main = document.selectFirst(".contson").select("p");
                String strs = "";
                for (Element el : main) {
                    strs = strs + el.text();
                }
                poem.content = strs;
            } else {
                Element main = document.selectFirst(".contson");
                poem.content = main.text();
            }
            if (document.selectFirst(".contyishang") != null) {
                if (document.selectFirst(".contyishang").select("div").text().contains("展开阅读全文") == false) {
                    poem.explanation = document.selectFirst(".contyishang").text();
                } else {
                    String id = document.selectFirst(".contyishang").select("div:eq(0)").select("div:eq(1)")
                            .select("span").attr("id").split("Play")[1];
                    String url01 = "https://so.gushiwen.org/shiwen2017/ajaxfanyi.aspx?id=";
                    url01 += id;
                    Document document01 = Jsoup.connect(url01).userAgent(agent).timeout(10000).get();
                    Elements explanation = document01.select(".contyishang p");
                    String strs = "";
                    for (Element el : explanation) {
                        strs = el.text() + "\n";
                    }
                    poem.explanation = strs;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getAuthorExplan(String url) {
        String text = "本文没有作者简介";
        try {
            Document document = Jsoup.connect(url).userAgent(agent).timeout(50000).get();
            if (document.select(".sonspic").text() != "") {
                text = document.select(".sonspic .cont").select("p").text().split("►")[0].replace(document.select(".sonspic .cont").select("b").text(), "");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return text;
    }
    public static List<Poem> searchByType(String url) {
        List<Poem> lists = new ArrayList<Poem>();
        int count = 1;
        Document doucment = null;
        try {
            doucment = Jsoup.connect(url).userAgent(agent).timeout(10000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            for (; count <= 1; count++) {
                Elements eles = doucment.select(".cont");
                for (Element ele : eles) {
                    for (final Element txt : ele.select(".source").prev()) {
                        Callable<Poem> callable = new Callable<Poem>() {
                            @Override
                            public Poem call() throws Exception {
                                Poem poem = new Poem(txt.text(), txt.select("a").attr("href"));
                                return poem;
                            }
                        };
                        FutureTask<Poem> task = new FutureTask<>(callable);
                        new Thread(task).start();

                        lists.add(task.get());

                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return lists;

    }
    public static List<String> getAuthor() {
        List<String> list = new ArrayList<>();
        String select = ".titletype #type2 a";
        String url = "https://www.gushiwen.org";
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.gushiwen.org/shiwen/").userAgent(agent).timeout(30000).get();
            Elements eles = doc.select(select);
            for (int i = 0; i < eles.size(); i++) {
                list.add(eles.get(i).text());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    public static Map<String, String> getType(String type) {
        String select = null;
        switch (type) {
            case "类型":
                select = ".titletype #type1 a";
                break;
            case "作者":
                select = ".titletype #type2 a";
                break;
            case "朝代":
                select = ".titletype #type3 a";
                break;
            case "形式":
                select = ".titletype > div:eq(4) a";
                break;
            default:
                select = ".titletype #type1 a";
                break;
        }
        Map<String, String> map = new HashMap<String, String>();
        String url = "https://www.gushiwen.org";
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.gushiwen.org/shiwen/").userAgent(agent).timeout(30000).get();
            Elements eles = doc.select(select);
            for (Element ele : eles) {
                map.put(ele.text(), url + ele.attr("href"));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
    public static void attrExplannationSound(Poem poem, Document document) {
        try {
            if (document.selectFirst(".contyishang h2").text().equals("译文及注释")
                    && document.select(".contyishang h2") != null) {
                String id = document.select(".contyishang").select("img").attr("id").split("Fanyi")[1];

                Document docs = Jsoup.connect("https://so.gushiwen.org/fanyiplay.aspx?id=" + id).userAgent(agent)
                        .timeout(1000).get();
                String urlsrc = docs.select("audio").attr("src");
                poem.explannationSound = urlsrc;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static String attrContentSound(String url) {
        String urlsrc = "";
        try {
            Document document = Jsoup.connect(url).userAgent(agent).timeout(3000).get();
            Elements ele = document.selectFirst(".tool").select(".toolpinglun:eq(3)");
            String id = ele.select("img").attr("id").replaceAll("[a-zA-Z]", "");
            if (!id.equals("")) {
                Document docs = Jsoup.connect("https://so.gushiwen.org/viewplay.aspx?id=" + id).userAgent(agent)
                        .timeout(1000).get();
                urlsrc = docs.select("audio").attr("src");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return urlsrc;
    }
    public static void poemShortcut(Poem poem) {
        try {
            Document document = Jsoup.connect(poem.url).userAgent(agent).timeout(10000).get();
            Element author = document.selectFirst(".cont .source");
            poem.author = author.text();
            if (document.selectFirst(".contson:has(p)") != null
                    && !document.selectFirst(".contson").select("p").text().equals("")) {
                Elements main = document.selectFirst(".contson").select("p");
                poem.shortcut = main.get(0).text().replace("　", "").substring(0, main.get(0).text().replace("　", "").indexOf("。") + 1);
            } else {
                Element main = document.selectFirst(".contson");
                poem.shortcut = main.text().replace("　", "").substring(0, main.text().replace("　", "").indexOf("。") + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getContent(String url) {
        String content = "本文没有内容";
        try {
            Document document = Jsoup.connect(url).userAgent(agent).timeout(10000).get();
            if (document.selectFirst(".contson:has(p)") != null
                    && !document.selectFirst(".contson").select("p").text().equals("")) {
                Elements main = document.selectFirst(".contson").select("p");
                String strs = "";
                for (Element el : main) {
                    strs = strs + el.text();
                }
                content = strs.replace("　", "");
                if ("".equals(content)) {
                    content = "本文没有内容";
                }
            } else {
                Element main = document.selectFirst(".contson");
                content = main.text().replace("　", "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    public static String getExplanation(String url) {
        String explanations = "本文没有注释";
        try {
            Document document = Jsoup.connect(url).userAgent(agent).timeout(50000).get();
            if (document.selectFirst(".contyishang") != null) {
                if (document.selectFirst(".contyishang").select("div").text().contains("展开阅读全文") == false) {
                    explanations = "注释: " + document.selectFirst(".contyishang").select("p").text().split("注释")[1];
                } else {
                    String id = document.selectFirst(".contyishang").select("div:eq(0)").select("div:eq(1)")
                            .select("span").attr("id").split("Play")[1];
                    String url01 = "https://so.gushiwen.org/shiwen2017/ajaxfanyi.aspx?id=";
                    url01 += id;
                    Document document01 = Jsoup.connect(url01).userAgent(agent).timeout(50000).get();
                    Elements explanation = document01.select(".contyishang p");
                    String strs = "";
                    if (explanation.text().split("注释").length != 1) {
                        strs = explanation.text().split("注释")[1];
                        explanations = strs.replace("▲", "");
                    }
                }
                if ("".equals(explanations)) {
                    explanations = "本文没有注释";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return explanations;
    }
    public static String getTranslation(String url) {
        String explanations = "本文没有译文";
        try {
            Document document = Jsoup.connect(url).userAgent(agent).timeout(50000).get();
            if (document.selectFirst(".contyishang") != null) {
                if (document.selectFirst(".contyishang").select("div").text().contains("展开阅读全文") == false) {
                    explanations = document.selectFirst(".contyishang").select("p").text().split("注释")[0];
                } else {
                    String id = document.selectFirst(".contyishang").select("div:eq(0)").select("div:eq(1)")
                            .select("span").attr("id").split("Play")[1];
                    String url01 = "https://so.gushiwen.org/shiwen2017/ajaxfanyi.aspx?id=";
                    url01 += id;
                    Document document01 = Jsoup.connect(url01).userAgent(agent).timeout(50000).get();
                    Elements explanation = document01.select(".contyishang p");
                    String strs = "";
                    if (explanation.text().split("注释").length != 0) {
                        strs = explanation.text().split("注释")[0].replace("译文", "");
                        explanations = strs.replace("▲", "");
                    }
                }
                if ("".equals(explanations)) {
                    explanations = "本文没有译文";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return explanations;
    }
}
