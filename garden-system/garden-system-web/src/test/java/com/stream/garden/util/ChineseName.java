package com.stream.garden.util;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class ChineseName {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            System.out.println(getSurname2() + " " + getChineseNames());
        }
        // print();
    }

    public static String getChineseName() {
        Random random=new Random();
//        int index=random.nextInt(Surname.length-1);
//        String name = Surname[index]; //获得一个随机的姓氏
        // 根据比例随机获取一个姓氏
        String name = getRandRateName();
		/* 从常用字中选取一个或两个字作为名 */
        if(random.nextBoolean()){
            name+=getChinese()+getChinese();
        }else {
            name+=getChinese();
        }
        return name;
    }

    public static String getSurname2() {
        int index = (int) (Math.random() * Surname2.length);
        return Surname2[index];
    }

    public static String getSurname() {
        return getRandRateName();
    }

    public static String getChineseNames() {
        if(0 == ((int)(Math.random() * 2))) {
            return (getChinese() + getChinese());
        }
        return getChinese();
    }

    /* 598 百家姓 */
    private static String[] Surname= {"赵","钱","孙","李","周","吴","郑","王","冯","陈","褚","卫","蒋","沈","韩","杨","朱","秦","尤","许",
            "何","吕","施","张","孔","曹","严","华","金","魏","陶","姜","戚","谢","邹","喻","柏","水","窦","章","云","苏","潘","葛","奚","范","彭","郎",
            "鲁","韦","昌","马","苗","凤","花","方","俞","任","袁","柳","酆","鲍","史","唐","费","廉","岑","薛","雷","贺","倪","汤","滕","殷",
            "罗","毕","郝","邬","安","常","乐","于","时","傅","皮","卞","齐","康","伍","余","元","卜","顾","孟","平","黄","和",
            "穆","萧","尹","姚","邵","湛","汪","祁","毛","禹","狄","米","贝","明","臧","计","伏","成","戴","谈","宋","茅","庞","熊","纪","舒",
            "屈","项","祝","董","梁","杜","阮","蓝","闵","席","季","麻","强","贾","路","娄","危","江","童","颜","郭","梅","盛","林","刁","钟",
            "徐","邱","骆","高","夏","蔡","田","樊","胡","凌","霍","虞","万","支","柯","昝","管","卢","莫","经","房","裘","缪","干","解","应",
            "宗","丁","宣","贲","邓","郁","单","杭","洪","包","诸","左","石","崔","吉","钮","龚","程","嵇","邢","滑","裴","陆","荣","翁","荀",
            "羊","于","惠","甄","曲","家","封","芮","羿","储","靳","汲","邴","糜","松","井","段","富","巫","乌","焦","巴","弓","牧","隗","山",
            "谷","车","侯","宓","蓬","全","郗","班","仰","秋","仲","伊","宫","宁","仇","栾","暴","甘","钭","厉","戎","祖","武","符","刘","景",
            "詹","束","龙","叶","幸","司","韶","郜","黎","蓟","溥","印","宿","白","怀","蒲","邰","从","鄂","索","咸","籍","赖","卓","蔺","屠",
            "蒙","池","乔","阴","郁","胥","能","苍","双","闻","莘","党","翟","谭","贡","劳","逄","姬","申","扶","堵","冉","宰","郦","雍","却",
            "璩","桑","桂","濮","牛","寿","通","边","扈","燕","冀","浦","尚","农","温","别","庄","晏","柴","瞿","阎","充","慕","连","茹","习",
            "宦","艾","鱼","容","向","古","易","慎","戈","廖","庾","终","暨","居","衡","步","都","耿","满","弘","匡","国","文","寇","广","禄",
            "阙","东","欧","殳","沃","利","蔚","越","夔","隆","师","巩","厍","聂","晁","勾","敖","融","冷","訾","辛","阚","那","简","饶","空",
            "曾","毋","沙","乜","养","鞠","须","丰","巢","关","蒯","相","查","后","荆","红","游","郏","竺","权","逯","盖","益","桓","公","仉",
            "督","岳","帅","缑","亢","况","郈","有","琴","归","海","晋","楚","闫","法","汝","鄢","涂","钦","商","牟","佘","佴","伯","赏","墨",
            "哈","谯","篁","年","爱","阳","佟","言","福","南","火","铁","迟","漆","官","冼","真","展","繁","檀","祭","密","敬","揭","舜","楼",
            "疏","冒","浑","挚","胶","随","高","皋","原","种","练","弥","仓","眭","蹇","覃","阿","门","恽","来","綦","召","仪","风","介","巨",
            "木","京","狐","郇","虎","枚","抗","达","杞","苌","折","麦","庆","过","竹","端","鲜","皇","亓","老","是","秘","畅","邝","还","宾",
            "闾","辜","纵","侴","万俟","司马","上官","欧阳","夏侯","诸葛","闻人","东方","赫连","皇甫","羊舌","尉迟","公羊","澹台","公冶","宗正",
            "濮阳","淳于","单于","太叔","申屠","公孙","仲孙","轩辕","令狐","钟离","宇文","长孙","慕容","鲜于","闾丘","司徒","司空","兀官","司寇",
            "南门","呼延","子车","颛孙","端木","巫马","公西","漆雕","车正","壤驷","公良","拓跋","夹谷","宰父","谷梁","段干","百里","东郭","微生",
            "梁丘","左丘","东门","西门","南宫","第五","公仪","公乘","太史","仲长","叔孙","屈突","尔朱","东乡","相里","胡母","司城","张廖","雍门",
            "毋丘","贺兰","綦毋","屋庐","独孤","南郭","北宫","王孙"};

    private static String[] Surname2 = {
            "万俟","司马","上官","欧阳","夏侯","诸葛","闻人","东方","赫连","皇甫","羊舌","尉迟","公羊","澹台","公冶","宗正",
            "濮阳","淳于","单于","太叔","申屠","公孙","仲孙","轩辕","令狐","钟离","宇文","长孙","慕容","鲜于","闾丘","司徒","司空","兀官","司寇",
            "南门","呼延","子车","颛孙","端木","巫马","公西","漆雕","车正","壤驷","公良","拓跋","夹谷","宰父","谷梁","段干","百里","东郭","微生",
            "梁丘","左丘","东门","西门","南宫","第五","公仪","公乘","太史","仲长","叔孙","屈突","尔朱","东乡","相里","胡母","司城","张廖","雍门",
            "毋丘","贺兰","綦毋","屋庐","独孤","南郭","北宫","王孙"
    };


    public static List<String> rateName = new LinkedList<>();
    public static Map<String, Double> rate = new HashMap<>();

    static {
        // 前100个姓氏占比，共87.2
        // 不存在当前比例中按照(100 - 87.2) / (姓总数 - 100)
        rate.put("李",7.94);
        rate.put("王",7.41);
        rate.put("张",7.07);
        rate.put("刘",5.38);
        rate.put("陈",4.53);
        rate.put("杨",3.08);
        rate.put("趙",2.29);
        rate.put("黄",2.23);
        rate.put("周",2.12);
        rate.put("吴",2.05);
        rate.put("徐",1.67);
        rate.put("孙",1.54);
        rate.put("胡",1.31);
        rate.put("朱",1.26);
        rate.put("高",1.21);
        rate.put("林",1.18);
        rate.put("何",1.17);
        rate.put("郭",1.15);
        rate.put("马",1.05);
        rate.put("罗",0.86);
        rate.put("梁",0.84);
        rate.put("宋",0.81);
        rate.put("郑",0.78);
        rate.put("谢",0.72);
        rate.put("韩",0.68);
        rate.put("唐",0.65);
        rate.put("冯",0.64);
        rate.put("于",0.62);
        rate.put("董",0.61);
        rate.put("萧",0.59);
        rate.put("程",0.57);
        rate.put("曹",0.57);
        rate.put("袁",0.54);
        rate.put("邓",0.54);
        rate.put("许",0.54);
        rate.put("傅",0.51);
        rate.put("沈",0.5);
        rate.put("曾",0.5);
        rate.put("彭",0.49);
        rate.put("吕",0.47);
        rate.put("苏",0.47);
        rate.put("卢",0.47);
        rate.put("蒋",0.47);
        rate.put("蔡",0.46);
        rate.put("魏",0.45);
        rate.put("贾",0.42);
        rate.put("丁",0.42);
        rate.put("薛",0.42);
        rate.put("叶",0.42);
        rate.put("阎",0.41);
        rate.put("余",0.41);
        rate.put("潘",0.41);
        rate.put("杜",0.4);
        rate.put("戴",0.39);
        rate.put("夏",0.39);
        rate.put("钟",0.38);
        rate.put("汪",0.37);
        rate.put("田",0.37);
        rate.put("任",0.37);
        rate.put("姜",0.36);
        rate.put("范",0.36);
        rate.put("方",0.36);
        rate.put("石",0.35);
        rate.put("姚",0.35);
        rate.put("谭",0.34);
        rate.put("廖",0.34);
        rate.put("邹",0.33);
        rate.put("熊",0.32);
        rate.put("金",0.32);
        rate.put("陆",0.31);
        rate.put("郝",0.3);
        rate.put("孔",0.29);
        rate.put("白",0.29);
        rate.put("崔",0.28);
        rate.put("康",0.28);
        rate.put("毛",0.27);
        rate.put("邱",0.27);
        rate.put("秦",0.26);
        rate.put("江",0.26);
        rate.put("史",0.25);
        rate.put("顾",0.25);
        rate.put("侯",0.25);
        rate.put("邵",0.24);
        rate.put("孟",0.24);
        rate.put("龙",0.24);
        rate.put("万",0.24);
        rate.put("段",0.23);
        rate.put("雷",0.23);
        rate.put("钱",0.22);
        rate.put("汤",0.19);
        rate.put("尹",0.19);
        rate.put("易",0.19);
        rate.put("黎",0.19);
        rate.put("常",0.18);
        rate.put("武",0.18);
        rate.put("乔",0.18);
        rate.put("贺",0.18);
        rate.put("赖",0.18);
        rate.put("龚",0.17);
        rate.put("文",0.17);

        initRateName();
    }

    public static String getRandRateName() {
//        Random random=new Random(System.currentTimeMillis());
//        int index=random.nextInt(rateName.size() - 1);
        int index = (int) (Math.random() * rateName.size());
        return rateName.get(index);
    }

    public static void initRateName() {
        // 四位小数
        Double total = 100D;
        Double sum100 = 0D;
        for (Double aDouble : rate.values()) {
            sum100 += aDouble;
        }
        System.out.println(sum100);
        Double b = total - sum100;
        Double c = b / (Surname.length - 100);
        System.out.println(b);
        System.out.println(Surname.length);
        System.out.println(c);
        for (String s : Surname) {
            Double d = rate.get(s);
            if(null == d) {
                d = c;
            }
            int e = (int) (d * 10000);
            for (int i = 0; i < e; i++) {
                rateName.add(s);
            }
        }
        Collections.shuffle(rateName);
        System.out.println(rateName.size());
    }

    public static String getChinese() {
        String str = null;
        int highPos, lowPos;
        Random random = new Random();
        highPos = (176 + Math.abs(random.nextInt(71)));//区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
        random=new Random();
        lowPos = 161 + Math.abs(random.nextInt(94));//位码，0xA0打头，范围第1~94列

        byte[] bArr = new byte[2];
        bArr[0] = (new Integer(highPos)).byteValue();
        bArr[1] = (new Integer(lowPos)).byteValue();
        try {
            str = new String(bArr, "GB2312");	//区位码组合成汉字
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if ("�".equals(str)) {
            return getChinese();
        }
        return str;
    }


}
