package com.hello.demo.myexcel;

import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: zhaohw
 * @date: 2022.01.12 下午 6:37
 */
public class ReadExcel01 {

    //读取excel并生成update的sql语句
    public static void main(String[] args) throws Exception {

        Map<String, Integer> organIdMap = new HashMap<>();

        //解析时间较长，所以把结果转成json字符串，方便调试
        String path0 = "c:/Users/user/Desktop/海纳万商历史合同——项目机构.xls";
        String path1 = "c:/Users/user/Desktop/海纳万商历史合同汇总V3——20220113.xls";
        String path2 = "c:/Users/user/Desktop/品质历史合同—规范数据.xlsx";
//        analysis(path0);
//        analysis(path1);
//        analysis(path2);
        //解析结果
//        String str = "{\"上海恒汇大厦管理处\":[\"NO.PZ-HTPSLC-SSHC-20200415-00006\",\"NO.PZ-HTPSLC-SSHC-20200426-00015\",\"NO.PZ-HTPSLC-SSHC-20200529-00019\",\"NO.PZ-HTPSLC-SSHC-20200601-00020\",\"NO.PZ-HTPSLC-SSHC-20200604-00021\",\"NO.PZ-HTPSLC-SSHC-20200604-00023\",\"NO.PZ-HTPSLC-SSHC-20200604-00024\",\"NO.PZ-HTPSLC-SSHC-20200604-00025\",\"NO.PZ-HTPSLC-SSHC-20200604-00026\",\"NO.PZ-HTPSLC-SSHC-20200604-00031\",\"NO.PZ-HTPSLC-SSHC-20200605-00036\",\"NO.PZ-HTPSLC-SSHC-20200605-00040\",\"NO.PZ-HTPSLC-SSHC-20200610-00047\",\"NO.PZ-HTPSLC-SSHC-20200706-00054\",\"NO.PZ-HTPSLC-SSHC-20200708-00069\",\"NO.PZ-HTPSLC-SSHC-20200708-00067\",\"NO.PZ-HTPSLC-SSHC-20200708-00066\",\"NO.PZ-HTPSLC-SSHC-20200709-00073\",\"NO.PZ-HTPSLC-SSHC-20200709-00074\"],\"中海物业管理有限公司洛阳分公司新安县第二人民医院\":[\"NO.PZ-HTPSLC-SYLY-20210825-00010\",\"NO.PZ-HTPSLC-SYLY-20211112-00018\",\"NO.PZ-HTPSLC-SYLY-20210930-00012\",\"NO.PZ-HTPSLC-SYLY-20210707-00007\",\"NO.PZ-HTPSLC-SYLY-20210610-00006\"],\"汕头潮宏基服务部管理处\":[\"NO.PZ-HTPSLC-SYST-20201229-00020\",\"NO.PZ-HTPSLC-SYST-20201229-00019\"],\"深圳中建钢构大厦管理处\":[\"NO.PZ-HTPSLC-SSZD-20210623-00004\"],\"南宁中移在线\":[\"NO.PZ-HTPSLC-SGZA-20190826-00002\"],\"珠海环宇城二期\":[\"NO.PZ-HTPSLC-SZHH-20210820-00001\"],\"三亚南航大厦管理处\":[\"NO.PZ-HTPSLC-SSZC-20200713-00008\",\"NO.PZ-HTPSLC-SSZC-20200911-00011\",\"NO.PZ-HTPSLC-SSZC-20210125-00002\"],\"长沙F1019\":[\"NO.PZ-HTPSLC-SBJD-20210607-00025\"],\"成都拓尔思天目中心管理处\":[\"NO.PZ-HTPSLC-SCDA-20200422-00001\",\"NO.PZ-HTPSLC-SCDA-20191014-00001\",\"NO.PZ-HTPSLC-SCDA-20201013-00004\",\"NO.PZ-HTPSLC-SCDA-20210818-00001\",\"NO.PZ-HTPSLC-CDSY-20180413-00005\"],\"深圳中海慧智大厦\":[\"NO.PZ-HTPSLC-SSZD-20210304-00002\",\"NO.PZ-HTPSLC-SSZD-20201119-00010\",\"NO.PZ-HTPSLC-SSZD-20201202-00012\",\"NO.PZ-HTPSLC-SSZD-20201203-00013\"],\"佛山中移在线（前期项目）\":[\"NO.PZ-HTPSLC-SYFS-20190903-00001\"],\"上海金鹤科技园管理处\":[\"NO.PZ-HTPSLC-SSHA-20190829-00010\",\"NO.PZ-HTPSLC-SSHA-20190925-00015\",\"NO.PZ-HTPSLC-SSHA-20190926-00016\",\"NO.PZ-HTPSLC-SSHA-20191021-00018\",\"NO.PZ-HTPSLC-SSHA-20191021-00020\",\"NO.PZ-HTPSLC-SSHA-20191031-00026\",\"NO.PZ-HTPSLC-SSHA-20191230-00037\",\"NO.PZ-HTPSLC-SSHA-20191230-00038\",\"NO.PZ-HTPSLC-SSHA-20191230-00039\",\"NO.PZ-HTPSLC-SSHA-20191230-00040\",\"NO.PZ-HTPSLC-SSHA-20200529-00077\",\"NO.PZ-HTPSLC-SSHA-20200608-00084\",\"NO.PZ-HTPSLC-SSHA-20200608-00085\",\"NO.PZ-HTPSLC-SSHA-20200608-00088\",\"NO.PZ-HTPSLC-SSHA-20200608-00091\",\"NO.PZ-HTPSLC-SSHA-20200608-00092\",\"NO.PZ-HTPSLC-SSHA-20200608-00093\",\"NO.PZ-HTPSLC-SSHA-20200608-00094\",\"NO.PZ-HTPSLC-SSHA-20200608-00095\",\"NO.PZ-HTPSLC-SSHA-20200608-00096\",\"NO.PZ-HTPSLC-SSHA-20200608-00097\",\"NO.PZ-HTPSLC-SSHA-20200608-00099\",\"NO.PZ-HTPSLC-SSHA-20200608-00100\",\"NO.PZ-HTPSLC-SSHA-20200608-00101\",\"NO.PZ-HTPSLC-SSHA-20200609-00105\",\"NO.PZ-HTPSLC-SSHA-20200609-00106\",\"NO.PZ-HTPSLC-SSHA-20200609-00107\",\"NO.PZ-HTPSLC-SSHA-20200609-00108\",\"NO.PZ-HTPSLC-SSHA-20200609-00109\",\"NO.PZ-HTPSLC-SSHA-20200609-00110\",\"NO.PZ-HTPSLC-SSHA-20200609-00111\",\"NO.PZ-HTPSLC-SSHA-20200609-00112\",\"NO.PZ-HTPSLC-SSHA-20200609-00113\",\"NO.PZ-HTPSLC-SSHA-20200609-00114\",\"NO.PZ-HTPSLC-SSHA-20200609-00115\",\"NO.PZ-HTPSLC-SSHA-20200609-00116\",\"NO.PZ-HTPSLC-SSHA-20200609-00117\",\"NO.PZ-HTPSLC-SSHA-20210125-00002\",\"NO.PZ-HTPSLC-SSHA-20190812-00006\",\"NO.PZ-HTPSLC-SSHA-20190812-00007\",\"NO.PZ-HTPSLC-SSHA-20190829-00009\",\"NO.PZ-HTPSLC-SSHA-20190919-00012\",\"NO.PZ-HTPSLC-SSHA-20190925-00014\",\"NO.PZ-HTPSLC-SSHA-20191021-00017\",\"NO.PZ-HTPSLC-SSHA-20191021-00019\",\"NO.PZ-HTPSLC-SSHA-20200325-00044\",\"NO.PZ-HTPSLC-SSHA-20200506-00073\",\"NO.PZ-HTPSLC-SSHA-20200708-00121\"],\"上海海昶美团管理处项目\":[\"NO.PZ-HTPSLC-SSHC-20210204-00002\"],\"汕头建设银行管理处\":[\"NO.PZ-HTPSLC-SYST-20210720-00009\",\"NO.PZ-HTPSLC-SYST-20210628-00011\"],\"驻马店国际会展中心\":[\"NO.PZ-HTPSLC-SBJA-20191219-00003\"],\"济南山东书城项目管理处\":[\"NO.PZ-HTPSLC-SYJN-20210126-00003\",\"NO.PZ-HTPSLC-SYJN-20190912-00004\"],\"东莞松山湖分公司广澳港区物业管理处\":[\"NO.PZ-HTPSLC-SYST-20210925-00014\"],\"天津中海大厦管理处\":[\"NO.PZ-HTPSLC-SYTJ-20190918-00002\",\"NO.PZ-HTPSLC-SYTJ-20191023-00009\",\"NO.PZ-HTPSLC-SYTJ-20200113-00003\"],\"沈阳环宇城项目分公司国际中心管理处\":[\"NO.PZ-HTPSLC-SYSY-20200612-00002\",\"NO.PZ-HTPSLC-SYSY-20201207-00005\",\"NO.PZ-HTPSLC-SYSY-20211012-00003\"],\"南京中建大厦管理处\":[\"NO.PZ-HTPSLC-SYNJ-20190731-00002\",\"NO.PZ-HTPSLC-SYNJ-20190905-00003\",\"NO.PZ-HTPSLC-SYNJ-20190918-00004\",\"NO.PZ-HTPSLC-SYNJ-20191223-00011\",\"NO.PZ-HTPSLC-SYNJ-20200520-00005\",\"NO.PZ-HTPSLC-SYNJ-20200718-00007\",\"NO.PZ-HTPSLC-SYNJ-20200915-00008\",\"NO.PZ-HTPSLC-SYNJ-20201208-00011\",\"NO.PZ-HTPSLC-SYNJ-20201218-00013\",\"NO.PZ-HTPSLC-SYNJ-20210331-00004\",\"NO.PZ-HTPSLC-SYNJ-20210427-00005\"],\"雄安市民中心\":[\"NO.PZ-HTPSLC-SBJC-20200430-00001\",\"NO.PZ-HTPSLC-SBJC-20210122-00001\",\"NO.PZ-HTPSLC-SBJC-20200921-00007\"],\"中海国际中心\":[\"NO.PZ-HTPSLC-SSHC-20200415-00005\",\"NO.PZ-HTPSLC-SSHC-20210121-00001\"],\"长沙环宇城管理处\":[\"NO.PZ-HTPSLC-SYFS-20210826-00003\"],\"北京奥南\":[\"NO.PZ-HTPSLC-SBJD-20201026-00068\",\"NO.PZ-HTPSLC-SBJD-20210122-00004\",\"NO.PZ-HTPSLC-SBJD-20190626-00008\",\"NO.PZ-HTPSLC-SBJD-20201106-00082\",\"NO.PZ-HTPSLC-SBJD-20201120-00083\",\"NO.PZ-HTPSLC-SBJD-20201126-00085\"],\"天津中海财富中心管理处\":[\"NO.PZ-HTPSLC-SYTJ-20191114-00010\",\"NO.PZ-HTPSLC-SYTJ-20200310-00006\",\"NO.PZ-HTPSLC-SYTJ-20191023-00008\",\"NO.PZ-HTPSLC-SYTJ-20200113-00004\"],\"汕头警备区管理处\":[\"NO.PZ-HTPSLC-SYST-20211013-00014\"],\"西安幸福林带管理处\":[\"NO.PZ-HTPSLC-SYGL-20200803-00017\"],\"北京石景山668管理处\":[\"No.PZ-HTPSLC-SBJA-20201014-00006\"],\"成都中海国际中心A区管理处\":[\"NO.PZ-HTPSLC-SCDC-20200330-00008\",\"NO.PZ-HTPSLC-SCDC-20210126-00001\",\"NO.HTHQ-CDF-CS-20170620-00196\",\"NO.PZ-HTPSLC-SCDC-20190719-00002\",\"NO.PZ-HTPSLC-SCDC-20200426-00022\",\"NO.PZ-HTPSLC-SCDC-20201120-00034\",\"NO.PZ-HTPSLC-SCDC-20201210-00035\"],\"腾讯云智研发武汉办公区\":[\"NO.PZ-HTPSLC-SYWH-20211012-00005\"],\"汕头中信大厦管理处\":[\"NO.PZ-HTPSLC-SYST-20210820-00011\",\"NO.PZ-HTPSLC-SYST-20210927-00013\"],\"深圳两馆项目\":[\"NO.PZ-HTPSLC--20190624-00002\"],\"宁夏中移在线\":[\"NO.PZ-HTPSLC-SYLY-20201023-00011\",\"NO.PZ-HTPSLC-SYGL-20191024-00012\"],\"北京百信银行项目\":[\"NO.SRM-ZYHTPS-SBJD-20210512-00058\",\"NO.SRM-ZYHTPS-SBJD-20210105-00002\",\"NO.PZ-HTPSLC-SBJD-20200521-00003\",\"NP.PZ-HTPSLC-SBJD-20191227-00003\"],\"武汉华为海思光工厂\":[\"NO.PZ-HTPSLC-SYWH-20210716-00001\"],\"汕头公安大院管理处\":[\"NO.PZ-HTPSLC-SYST-20210112-00001\",\"NO.PZ-HTPSLC-SYST-20210309-00002\",\"NO.PZ-HTPSLC-SYST-20210408-00004\",\"NO.PZ-HTPSLC-SYST-20210406-00003\",\"NO.PZ-HTPSLC-SYST-20210508-00005\",\"NO.PZ-HTPSLC-SYST-20210526-00006\",\"NO.PZ-HTPSLC-SYST-20210812-00010\",\"NO.PZ-HTPSLC-SYST-20190826-00003\"],\"汕头中移在线\":[\"NO.PZ-HTPSLC-SYST-20190903-00004\"],\"上海海兴管理处\":[\"NO.PZ-HTPSLC-SSHB-20201123-00001\",\"NO.PZ-HTPSLC-SSHB-20201201-00003\"],\"武汉中海大厦\":[\"NO.PZ-HTPSLC-SYWH-20190924-00001\"],\"西安中海大厦管理处\":[\"NO.PZ-HTPSLC-SXAX-20201218-00001\"],\"成都中海国际中心C区管理处\":[\"NO.PZ-HTPSLC-SCDC-20200415-00015\",\"NO.PZ-HTPSLC-SCDC-20200422-00020\",\"NO.PZ-HTPSLC-SCDC-20200326-00007\"],\"佛山寰宇天地管理处\":[\"NO.PZ-HTPSLC-SYFS-20191030-00003\",\"NO.PZ-HTPSLC-SYFS-20191206-00005\",\"NO.PZ-HTPSLC-SYFS-20191206-00006\"],\"东莞中移在线\":[\"NO.PZ-HTPSLC--20190903-00002\"],\"兰州中海广场(商业)管理处\":[\"NO.PZ-HTPSLC-SYGL-20201103-00037\",\"NO.PZ-HTPSLC-SYLZ-20210113-00001\",\"NO.PZ-HTPSLC-SYGL-20200702-00023\",\"NO.PZ-HTPSLC-SYLZ-20201222-00004\",\"NO.PZ-HTPSLC-SYLZ-20210125-00002\",\"NO.PZ-HTPSLC-SYLZ-20210126-00003\"],\"上海广场管理处\":[\"NO.PZ-HTPSLC-SSHB-20210125-00002\",\"NO.PZ-HTPSLC-SSHB-20210420-00003\",\"NO.PZ-HTPSLC-SSHC-20200416-00012\"],\"华为贵安七星谷管理处\":[\"NO.PZ-HTPSLC-SCDA-20210907-00004\"],\"滕州德意君瑞城北区管理处\":[\"NO.PZ-HTPSLC-SYJN-20200703-00002\"],\"天津财富中心管理处\":[\"NO.PZ-HTPSLC-SYTJ-20190918-00003\"],\"天津中海广场管理处\":[\"NO.PZ-HTPSLC-SYTJ-20191114-00011\",\"NO.PZ-HTPSLC-SYTJ-20200310-00005\",\"NO.PZ-HTPSLC-SYTJ-20210429-00010\",\"NO.PZ-HTPSLC-BJSY-20181114-00798\",\"NO.PZ-HTPSLC-SYTJ-20190918-00001\",\"NO.PZ-HTPSLC-SYTJ-20191023-00007\",\"NO.PZ-HTPSLC-SYTJ-20200113-00002\"],\"中国共产党历史展览馆管理处\":[\"NO.PZ-HTPSLC-SBJB-20201224-00007\"],\"中海大厦项目物业管理处\":[\"NO.PZ-HTPSLC-SCDB-20200422-00002\",\"NO.PZ-HTPSLC-SCDB-20200624-00005\",\"NO.PZ-HTPSLC-CDSY-20181207-00008\",\"NO.PZ-HTPSLC-SCDB-20191231-00010\",\"NO.PZ-HTPSLC-CDSY-20190227-00004\"],\"洛龙区翠云路街道办管理处\":[\"NO.PZ-HTPSLC-SYLY-20210923-00011\",\"NO.PZ-HTPSLC-SYLY-20201026-00012\",\"NO.PZ-HTPSLC-SYLY-20200403-00002\",\"NO.PZ-HTPSLC-SYLY-20210802-00009\"],\"北京中海海都管理处\":[\"NO.PZ-HTPSLC-SBJD-20210127-00010\",\"NO.PZ-HTPSLC-SBJD-20190604-00002\",\"NO.PZ-HTPSLC-SBJD-20190604-00003\",\"NO.PZ-HTPSLC-SBJD-20190605-00004\",\"NO.PZ-HTPSLC-SBJD-20190605-00005\",\"NO.PZ-HTPSLC-SBJD-20190605-00006\",\"NO.PZ-HTPSLC-SBJD-20190612-00007\",\"NO.PZ-HTPSLC-SBJD-20190710-00011\",\"NO.PZ-HTPSLC-SBJD-20191212-00053\",\"NO.PZ-HTPSLC-SBJD-20191210-00050\",\"NO.PZ-HTPSLC-SBJD-20201019-00063\",\"NO.PZ-HTPSLC-SBJD-20201019-00064\",\"NO.PZ-HTPSLC-SBJD-20201027-00070\",\"NO.PZ-HTPSLC-SBJD-20201027-00071\",\"NO.PZ-HTPSLC-SBJD-20201028-00074\",\"NO.PZ-HTPSLC-SBJD-20201030-00075\",\"NO.PZ-HTPSLC-SBJD-20201130-00086\",\"NO.PZ-HTPSLC-SBJD-20201209-00090\",\"NO.PZ-HTPSLC-SBJD-20201209-00092\",\"NO.PZ-HTPSLC-SBJD-20201209-00091\",\"NO.PZ-HTPSLC-SBJD-20201215-00093\",\"NO.PZ-HTPSLC-SBJD-20210425-00020\",\"NO.PZ-HTPSLC-SBJD-20211001-00054\"],\"北京中海大厦(中海广场)管理处\":[\"NO.PZ-HTPSLC-SBJA-20200415-00004\",\"NO.PZ-HTPSLC-BJSY-20180307-00002\"],\"成都东部集团\":[\"NO.PZ-HTPSLC-SCDB-20210623-00006\",\"NO.PZ-HTPSLC-SCDB-20210623-00005\"],\"长春申通物流园管理处\":[\"NO.PZ-HTPSLC-SYGL-20210315-00003\"],\"深圳中海商城管理处\":[\"NO.PZ-HTPSLC-SSZC-20210513-00011\"],\"沈阳中海山姆会员店管理处\":[\"NO.PZ-HTPSLC-SYSY-20201221-00008\",\"NO.PZ-HTPSLC-SYSY-20210122-00001\"],\"成都中海右岸环宇坊管理处\":[\"NO.PZ-HTPSLC-SCDB-20210118-00002\"],\"北京古城中海大厦管理处\":[\"NO.PZ-HTPSLC-SBJB-20201208-00014\",\"NO.PZ-HTPSLC-SBJB-20210126-00002\",\"NO.PZ-HTPSLC-SBJB-20201231-00021\",\"NO.PZ-HTPSLC-SBJB-20210106-00001\",\"NO.PZ-HTPSLC-SBJB-20210329-00005\",\"NO.PZ-HTPSLC-BJSY-20171219-00009\"],\"中建技术中心\":[\"NO.PZ-HTPSLC-SBJD-20210111-00001\"],\"广州锦城大厦管理处\":[\"NO.PZ-HTPSLC-SGZB-20200416-00003\"],\"福建外贸大厦管理处\":[\"NO.PZ-HTPSLC-SGZA-20200814-00002\"],\"广州中建八局办公楼\":[\"NO.PZ-HTPSLC-SGZA-20190710-00001\"],\"基准方中\":[\"NO.PZ-HTPSLC-SCDB-20210401-00002\",\"NO.PZ-HTPSLC-SCDB-20210413-00003\"],\"石家庄国际会展中心\":[\"NO.PZ-HTPSLC-BJSY-20181129-00009\"],\"广州海珠米立方\":[\"NO.PZ-HTPSLC-SGZA-20211008-00008\"],\"31421部队营区\":[\"NO.PZ-HTPSLC-SYSY-20210731-00001\"],\"国家开发银行河北雄安分行管理处\":[\"NO.PZ-HTPSLC-SBJC-20190927-00001\"],\"深圳腾讯深汕管理处\":[\"NO.PZ-HTPSLC-SSZA-20200423-00002\",\"NO.PZ-HTPSLC-SSZA-20201224-00007\",\"NO.PZ-HTPSLC-SSZA-20210831-00002\",\"NO.PZ-HTPSLC--20191030-00002\",\"NO.PZ-HTPSLC-SSZA-20201022-00005\",\"NO.PZ-HTPSLC-SSZA-20210831-00003\",\"NO.PZ-HTPSLC--20191030-00002(1)\"],\"南通紫琅科技城\":[\"NO.PZ-HTPSLC-SYHZ-20200820-00008\"],\"深圳敦煌大厦管理处\":[\"NO.PZ-HTPSLC-SSZC-20201210-00014\"],\"成都中海财富中心\":[\"NO.PZ-HTPSLC-SCDB-20191219-00008\",\"NO.PZ-HTPSLC-SCDB-20200422-00001\",\"NO.PZ-HTPSLC-SCDB-20210803-00005\",\"NO.PZ-HTPSLC-SCDB-20190919-00004\"],\"珠海富华里管理处\":[\"NO.PZ-HTPSLC-ZHSY-20201204-00001\",\"NO.PZ-HTPSLC-ZHSY-20210225-00002\"],\"太原中海国际中心管理处\":[\"NO.PZ-HTPSLC-SBJB-20210401-00006\",\"NO.PZ-HTPSLC-SBJB-20190916-00001\",\"NO.PZ-HTPSLC-SBJB-20191022-00005\",\"NO.PZ-HTPSLC-SBJB-20191029-00007\",\"NO.PZ-HTPSLC-SBJB-20191213-00009\",\"NO.PZ-HTPSLC-SBJB-20200911-00012\",\"NO.PZ-HTPSLC-BJSY-20181120-00842\",\"NO.PZ-HTPSLC-BJSY-20190322-00007\",\"NO.PZ-HTPSLC-SBJB-20200508-00007\"],\"北京平安金融中心\":[\"NO.PZ-HTPSLC-SYJL-20200628-00010\",\"NO.PZ-HTPSLC-SBJB-20201222-00015\",\"NO.PZ-HTPSLC-SBJB-20201224-00016\",\"NO.PZ-HTPSLC-SBJB-20201225-00017\"],\"成都中海大厦管理处\":[\"NO.PZ-HTPSLC-SCDC-20200825-00030\"],\"北京惠通时代广场管理处\":[\"NO.PZ-HTPSLC-SBJB-20191022-00006\",\"NO.PZ-HTPSLC-BJSY-20181207-00011\"],\"洛阳中移产业园管理处\":[\"NO.PZ-HTPSLC-SYLY-20201028-00019\",\"NO.PZ-HTPSLC-SYLY-20201027-00017\",\"NO.PZ-HTPSLC-SYLY-20201027-00016\",\"NO.PZ-HTPSLC-SYLY-20201027-00015\",\"NO.PZ-HTPSLC-SYLY-20201027-00014\",\"NO.PZ-HTPSLC-SYLY-20201027-00013\",\"NO.PZ-HTPSLC-SYLY-20210721-00008\",\"NO.PZ-HTPSLC-SYLY-20210528-00005\",\"NO.PZ-HTPSLC-SYLY-20201225-00022\",\"NO.PZ-HTPSLC-SYLY-20211101-00017\",\"NO.PZ-HTPSLC-SYLY-20210203-00002\"],\"深圳世贸广场管理处\":[\"NO.PZ-HTPSLC-SSZC-20201210-00013\",\"NO.PZ-HTPSLC-SSZC-20210518-00012\",\"NO.PZ-HTPSLC-SSZC-20210917-00019\",\"09-01-0451\",\"NO.PZ-HTPSLC-SSZC-20210820-00018\"],\"佛山中海环宇城\":[\"NO.PZ-HTPSLC-SYFS-20191030-00004\",\"NO.PZ-HTPSLC-SYFS-20210111-00001\"],\"汕头南滨南路66号院物业管理处\":[\"NO.PZ-HTPSLC-SYST-20210603-00007\"],\"汕头中信滨海新城管理处\":[\"NO.PZ-HTPSLC-SYST-20201207-00017\"],\"杭州中海发展大厦\":[\"NO.PZ-HTPSLC-SYHZ-20200611-00005\",\"NO.PZ-HTPSLC-SYHZ-20210113-00002\",\"NO.PZ-HTPSLC-SYHZ-20210318-00004\"],\"成都交子金融大街管理处\":[\"NO.PZ-HTPSLC-SCDC-20201230-00001\"],\"北京中建紫竹酒店管理处\":[\"NO.PZ-HTPSLC-SBJD-20200927-00048\",\"NO.PZ-HTPSLC-SBJD-20210122-00006\"],\"苏州中海财富中心\":[\"NO.PZ-HTPSLC-SSHA-20200117-00002\",\"NO.PZ-HTPSLC-SSHA-20200213-00003\",\"NO.PZ-HTPSLC-SSHA-20200225-00004\",\"NO.PZ-HTPSLC-SSHA-20200310-00035\",\"NO.PZ-HTPSLC-SSHA-20200612-00118\",\"NO.PZ-HTPSLC-SSHA-20210127-00005\",\"NO.PZ-HTPSLC-SSHA-20210126-00003\",\"NO.PZ-HTPSLC-SSHA-20190920-00013\",\"NO.PZ-HTPSLC-SSHA-20200819-00124\",\"NO.PZ-HTPSLC-SSHA-20200225-00005\",\"NO.PZ-HTPSLC-SSHA-20200309-00011\",\"NO.PZ-HTPSLC-SSHA-20200309-00013\",\"NO.PZ-HTPSLC-SSHA-20200309-00021\",\"NO.PZ-HTPSLC-SSHA-20200309-00022\",\"NO.PZ-HTPSLC-SSHA-20200309-00023\",\"NO.PZ-HTPSLC-SSHA-20200309-00024\",\"NO.PZ-HTPSLC-SSHA-20200309-00028\",\"NO.PZ-HTPSLC-SSHA-20200309-00029\",\"NO.PZ-HTPSLC-SSHA-20200309-00030\",\"NO.PZ-HTPSLC-SSHA-20200309-00033\",\"NO.PZ-HTPSLC-SSHA-20200311-00036\",\"NO.PZ-HTPSLC-SSHA-20200311-00037\",\"NO.PZ-HTPSLC-SSHA-20200320-00039\",\"NO.PZ-HTPSLC-SSHA-20200320-00040\",\"NO.PZ-HTPSLC-SSHA-20200320-00041\",\"NO.PZ-HTPSLC-SSHA-20200622-00119\"],\"郑州河南中移在线管理处\":[\"NO.PZ-HTPSLC-SYLY-20210120-00001\"],\"郑州中建创业大厦管理处\":[\"NO.PZ-HTPSLC-SBJA-20200929-00017\",\"NO.PZ-HTPSLC-SBJA-20200929-00020\",\"NO.PZ-HTPSLC-BJSY-20180626-00133\"],\"北京中海置业大厦(中海地产广场)管理处\":[\"NO.PZ-HTPSLC-SBJA-20200929-00016\",\"NO.PZ-HTPSLC-SBJA-20200929-00018\",\"NO.PZ-HTPSLC-BJSY-20180308-00003\",\"NO.PZ-HTPSLC-SBJA-20191016-00016\"],\"上海中建大厦管理处\":[\"NO.PZ-HTPSLC-SSHA-20200415-00048\",\"NO.PZ-HTPSLC-SSHA-20200326-00046\"],\"北京中海宏洋(中海国际中心)管理处\":[\"NO.PZ-HTPSLC-SBJD-20190918-00017\",\"NO.PZ-HTPSLC-SBJA-20200929-00019\",\"NO.PZ-HTPSLC-SBJA-20190829-00008\",\"NO.PZ-HTPSLC-SBJA-20200420-00002\",\"NO.PZ-HTPSLC-BJSY-20171227-00010\",\"NO.PZ-HTPSLC-SBJD-20200928-00049\",\"NO.PZ-HTPSLC-SBJD-20210413-00018\",\"NO.PZ-HTPSLC-SBJD-20210507-00022\",\"NO.PZ-HTPSLC-SBJD-20210903-00047\",\"NO.PZ-HTPSLC-SBJD-20210903-00048\",\"NO.PZ-HTPSLC-SBJD-20210903-00049\"],\"保定深圳园燕云城项目（前期项目）\":[\"NO.PZ-HTPSLC-SBJC-20201202-00003\"],\"成都中建大厦管理处\":[\"NO.PZ-HTPSLC-SCDA-20200422-00002\",\"NO.HTHQ-CQF-CS-20161221-00045\",\"NO.PZ-HTPSLC-CDSY-20181101-00702\"],\"青岛中海大厦管理处\":[\"NO.PZ-HTPSLC-SYGL-20200628-00021\"],\"福建平安职场管理处\":[\"NO.PZ-HTPSLC-SGZA-20200427-00001\"],\"南京环宇城管理处\":[\"NO.PZ-HTPSLC-SYNJ-20191120-00009\",\"NO.PZ-HTPSLC-SYNJ-20210120-00001\"],\"深圳腾讯科兴物业管理处\":[\"NO.PZ-HTPSLC--20191030-00001\",\"NO.PZ-HTPSLC-GDSZ-20181025-00650\",\"NO.PZ-HTPSLC-SSZA-20201228-00004\",\"NO.PZ-HTPSLC-SSZA-20200812-00002\",\"NO.PZ-HTPSLC-SSZA-20200804-00001\",\"NO.PZ-HTPSLC-SSZA-20211027-00006\"],\"成都中海国际中心B区管理处\":[\"NO.PZ-HTPSLC-SCDC-20200422-00018\",\"NO.PZ-HTPSLC-SCDC-20200331-00009\"],\"腾讯教育郑州智慧岛职场\":[\"NO.PZ-HTPSLC-SBJA-20210907-00007\"],\"中海天府环宇坊管理处\":[\"NO.PZ-HTPSLC-SCDB-20201217-00013\",\"NO.PZ-HTPSLC-SCDB-20210119-00003\"],\"天津津信海富园区管理处\":[\"NO.PZ-HTPSLC-SYTJ-20210429-00011\",\"NO.PZ-HTPSLC-SYTJ-20210513-00013\",\"NO.PZ-HTPSLC-SYTJ-20210705-00014\",\"NO.PZ-HTPSLC-SYTJ-20210325-00006\"],\"济南环宇城管理处\":[\"NO.PZ-HTPSLC-SYJN-20210126-00004\",\"NO.PZ-HTPSLC-SYJN-20210204-00007\",\"NO.PZ-HTPSLC-SYJN-20191227-00008\",\"NO.PZ-HTPSLC-SYJN-20201231-00010\"]}\n";
//        String str1 = "{\"北京中海宏洋(中海国际中心)管理处\":[\"NO.PZ-HTPSLC-SBJA-20210907-00007\"],\"天津中海财富中心管理处\":[\"NO.PZ-HTPSLC-SYTJ-20190918-00003\"],\"洛阳中移产业园管理处\":[\"NO.PZ-HTPSLC-SGZA-20190826-00002\",\"NO.PZ-HTPSLC--20190903-00002\",\"NO.PZ-HTPSLC-SYFS-20190903-00001\"],\"成都拓尔思天目中心管理处\":[\"NO.PZ-HTPSLC-SCDA-20201013-00004\"],\"沈阳国际中心大厦分公司国际中心管理处\":[\"NO.PZ-HTPSLC-SYSY-20200612-00002\",\"NO.PZ-HTPSLC-SYSY-20201207-00005\",\"NO.PZ-HTPSLC-SYSY-20211012-00003\"],\"雄安市民中心\":[\"NO.PZ-HTPSLC-SBJC-20190927-00001\"],\"华为海思项目\":[\"NO.PZ-HTPSLC-SYWH-20210716-00001\"],\"北京奥南管理处\":[\"NO.SRM-ZYHTPS-SBJD-20210512-00058\",\"NO.SRM-ZYHTPS-SBJD-20210105-00002\",\"NO.PZ-HTPSLC-SBJD-20200521-00003\",\"NO.PZ-HTPSLC-SBJD-20201026-00068\",\"NO.PZ-HTPSLC-SBJD-20210122-00004\",\"NO.PZ-HTPSLC-SBJD-20210111-00001\",\"NO.PZ-HTPSLC-SBJD-20210607-00025\",\"NP.PZ-HTPSLC-SBJD-20191227-00003\",\"NO.PZ-HTPSLC-SBJD-20190626-00008\",\"NO.PZ-HTPSLC-SBJD-20201106-00082\",\"NO.PZ-HTPSLC-SBJD-20201120-00083\",\"NO.PZ-HTPSLC-SBJD-20201126-00085\"],\"成都中海大厦管理处\":[\"NO.PZ-HTPSLC-SCDB-20200422-00002\",\"NO.PZ-HTPSLC-SCDB-20200624-00005\",\"NO.PZ-HTPSLC-SCDB-20210623-00006\",\"NO.PZ-HTPSLC-SCDB-20210623-00005\",\"NO.PZ-HTPSLC-CDSY-20181207-00008\",\"NO.PZ-HTPSLC-SCDB-20191231-00010\",\"NO.PZ-HTPSLC-CDSY-20190227-00004\"],\"沈阳国际中心大厦中海山姆会员店管理处\":[\"NO.PZ-HTPSLC-SYSY-20201221-00008\",\"NO.PZ-HTPSLC-SYSY-20210731-00001\"],\"武汉腾讯项目\":[\"NO.PZ-HTPSLC-SYWH-20211012-00005\"],\"上海国际中心管理处\":[\"NO.PZ-HTPSLC-SSHC-20200415-00005\",\"NO.PZ-HTPSLC-SSHC-20210121-00001\"]}\n";
        String str2 = "{\"福州中海锦园管理处\":[\"NO.PZ-HTPSLC-ZGFJ-20210914-00077\"],\"哈尔滨中海时代公馆管理处\":[\"NO.PZ-HTPSLC-HLJHEB-20210806-00027\"],\"大连中海云麓公馆管理处\":[\"NO.PZ-HTPSLC-LNDL-20211027-00167\",\"NO.PZ-HTPSLC-LNDL-20211027-00168\",\"NO.PZ-HTPSLC-LNDL-20211027-00169\"],\"青岛中海红著管理处\":[\"NO.PZ-HTPSLC-SDQD-20210823-00071\"],\"成都中信城右岸管理处\":[\"NO.PZ-HTPSLC-SCCD-20210322-00025\",\"NO.PZ-HTPSLC-SCCD-20210322-00026\"],\"哈尔滨中海和院管理处\":[\"NO.PZ-HTPSLC-HLJHEB-20210720-00020\"],\"长春中海盛世城CD区管理处\":[\"NO.PZ-HTPSLC-JLCC-20210518-00028\"],\"吉林中海悦江府管理处\":[\"NO.PZ-HTPSLC-JLJL-20210520-00031\",\"NO.PZ-HTPSLC-JLJL-20210520-00032\",\"NO.PZ-HTPSLC-JLJL-20211011-00041\"],\"吉林国际社区一区管理处\":[\"NO.PZ-HTPSLC-JLJL-20210512-00022\"],\"成都中海云麓世家管理处\":[\"NO.PZ-HTPSLC-SCCD-20210430-00033\"],\"长春中海盛世城B区管理处\":[\"NO.PZ-HTPSLC-JLCC-20210818-00050\"],\"吉林中海国际社区伴山郡管理处\":[\"NO.PZ-HTPSLC-JLJL-20210512-00026\"],\"吉林中海国际社区一区第二管理处\":[\"NO.PZ-HTPSLC-JLJL-20210512-00030\"],\"济南中海华山凯旋门管理处\":[\"NO.PZ-HTPSLC-SDJN-20210525-00029\"],\"吉林市国际社区三区管理处\":[\"NO.PZ-HTPSLC-JLJL-20210512-00025\"],\"杭州御道江河汇流公寓四区管理处\":[\"NO.PZ-HTPSLC-ZJHZ-20191010-00030\"],\"济南中海华山云著管理处\":[\"NO.PZ-HTPSLC-SDJN-20211018-00057\"],\"福州中海锦城花园管理处\":[\"NO.PZ-HTPSLC-ZGFJ-20210406-00024\"],\"厦门中海九号公馆管理处\":[\"NO.PZ-HTPSLC-ZGFJ-20210809-00049\"],\"吉林中海国际社区铂悦公馆东区管理处\":[\"NO.PZ-HTPSLC-JLJL-20210512-00027\",\"NO.PZ-HTPSLC-JLJL-20210512-00028\"],\"厦门中海寰宇天下管理处\":[\"NO.PZ-HTPSLC-ZGFJ-20210722-00044\"],\"吉林中海新都会管理处\":[\"NO.PZ-HTPSLC-JLJL-20210512-00024\"],\"青岛中海樘院管理处\":[\"NO.PZ-HTPSLC-SDQD-20210420-00030\",\"NO.PZ-HTPSLC-SDQD-20210416-00027\"],\"东莞凯旋国际管理处\":[\"NO.PZ-HTPSLC-GDDG-20210519-00015\",\"NO.PZ-HTPSLC-GDDG-20210519-00016\"],\"吉林中海大厦管理处\":[\"NO.PZ-HTPSLC-JLJL-20210511-00021\"],\"吉林紫御江城管理处\":[\"NO.PZ-HTPSLC-JLJL-20210512-00023\"],\"昆明中海寰宇花园管理处\":[\"NO.PZ-HTPSLC-YNKM-20210506-00002\"],\"成都中海物业管理有限公司昆明分公司\":[\"NO.PZ-HTPSLC-YNKM-20210630-00006\",\"NO.PZ-HTPSLC-YNKM-20210630-00005\"],\"哈尔滨中海时代管理处\":[\"NO.PZ-HTPSLC-HLJHEB-20210806-00026\"],\"吉林中海环宇天地管理处\":[\"NO.PZ-HTPSLC-JLJL-20210512-00029\"]}\n";

        String jsonStr = str2;
        if (jsonStr.isEmpty()) return;

        Map<String, List<String>> contractMap = (Map) JSON.parseObject(jsonStr);

        printInOrgan(contractMap);

//        organIdMapInit(organIdMap);
//        organIdMapInit2(organIdMap);
        organIdMapInit3(organIdMap);

        List<String> errorList = new ArrayList<>();
        List<String> nullVal = new ArrayList<>();
        contractMap.forEach((organName, codeList) -> {
            String temp = "-- %s \nupdate ctt_contract set organ_id=%s where contract_code in (%s);";
            Integer organId = organIdMap.get(organName);
            String codeStr = codeList.stream().map(code -> String.format("'%s'", code))
                    .collect(Collectors.joining(","));
            String format = String.format(temp, organName, organId, codeStr);
            if (Objects.nonNull(organId)) {
                //输出update sql语句
                System.out.println(format);
            } else {
                errorList.add(format);
            }

            if (Objects.isNull(organId)) nullVal.add(organName);
        });
        System.out.println();
        //输出错误的update sql语句
        if (!errorList.isEmpty()) {
            System.out.println("-- 无法匹配机构的数据");
            for (String s : errorList) {
                System.out.println(s);
            }
        }

        System.out.println();

        //输出未查询到的机构名称
        String temp = "select * from copm_b.organ where status=1 and name in (%s);";
        System.out.println(String.format(temp, nullVal.stream().map(name -> String.format("'%s'", name))
                .collect(Collectors.joining(","))));
    }

    private static Map<String, List<String>> analysis(String path) throws Exception {
        System.out.println("start analysis ...");
        Workbook workbook = WorkbookFactory.create(new File(path));

        System.out.println("start read ...");
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, List<String>> map0 = new HashMap<>();

        for (int i = 3; i < sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            Cell organCell = row.getCell(4);
            Cell codeCell = row.getCell(3);

            String organName = organCell.getStringCellValue().trim();
            String contractCode = codeCell.getStringCellValue().trim();

            if (organName.isEmpty() || contractCode.isEmpty()) continue;

            List<String> codeList = map0.getOrDefault(organName, new ArrayList<>());
            codeList.add(contractCode);
            map0.put(organName, codeList);
        }

        System.out.println(JSON.toJSONString(map0));

        workbook.close();
        return map0;
    }

    private static void printInOrgan(Map<String, List<String>> map) {
        List<List<String>> list = new ArrayList<>();
        int i = 0;
        List<String> list0 = new ArrayList<>();
        for (String s : map.keySet()) {
            list0.add(s);
            if (i++ >= 10) {
                list.add(list0);
                list0 = new ArrayList<>();
                i = 0;
            }
        }
        list.add(list0);

        for (List<String> strings : list) {
            System.out.println(strings.stream().map(str -> String.format("'%s'", str))
                    .collect(Collectors.joining(",", "select concat('map.put(\"', name, '\",', organ_id, ');') from copm_b.organ where name in(",
                            ") and orgtype=6 and status=1 order by name;")));
            System.out.println();
        }
    }

    private static void organIdMapInit(Map<String, Integer> map) {

        map.put("三亚南航大厦管理处", 12254641);
        map.put("上海恒汇大厦管理处", 12254508);
        map.put("中海物业管理有限公司洛阳分公司新安县第二人民医院", 12634352);
        map.put("成都拓尔思天目中心管理处", 12464281);
        map.put("汕头潮宏基服务部管理处", 12254653);
        map.put("深圳中建钢构大厦管理处", 12254511);
        map.put("深圳中海慧智大厦", 12484310);
        map.put("珠海环宇城二期", 12484350);

        map.put("上海海昶美团管理处项目", 12484332);
        map.put("上海金鹤科技园管理处", 12254509);
        map.put("东莞松山湖分公司广澳港区物业管理处", 12524259);
        map.put("南京中建大厦管理处", 12254646);
        map.put("天津中海大厦管理处", 12244952);
        map.put("汕头建设银行管理处", 12254657);
        map.put("济南山东书城项目管理处", 12254967);
        map.put("雄安市民中心", 12514313);
        map.put("驻马店国际会展中心", 12544342);

        map.put("北京石景山668管理处", 12464422);
        map.put("天津中海财富中心管理处", 12244953);
        map.put("宁夏中移在线", 12454273);
        map.put("成都中海国际中心A区管理处", 12244223);
        map.put("汕头中信大厦管理处", 12254652);
        map.put("汕头警备区管理处", 12254660);
        map.put("深圳两馆项目", 12384264);
        map.put("西安幸福林带管理处", 12464284);
        map.put("长沙环宇城管理处", 12244504);

        map.put("上海海兴管理处", 12192459);
        map.put("佛山寰宇天地管理处", 12284346);
        map.put("兰州中海广场(商业)管理处", 12444266);
        map.put("成都中海国际中心C区管理处", 12244225);
        map.put("武汉中海大厦", 12454263);
        map.put("汕头中移在线", 12434279);
        map.put("汕头公安大院管理处", 12254658);
        map.put("西安中海大厦管理处", 12254664);

        map.put("上海广场管理处", 12254506);
        map.put("中国共产党历史展览馆管理处", 12464437);
        map.put("北京中海大厦(中海广场)管理处", 12182472);
        map.put("北京中海海都管理处", 12254251);
        map.put("华为贵安七星谷管理处", 12604269);
        map.put("天津中海广场管理处", 12244951);
        map.put("洛龙区翠云路街道办管理处", 12464435);
        map.put("滕州德意君瑞城北区管理处", 12544300);

        map.put("北京古城中海大厦管理处", 12254214);
        map.put("基准方中", 12664277);
        map.put("广州中建八局办公楼", 12464262);
        map.put("广州锦城大厦管理处", 12182465);
        map.put("成都中海右岸环宇坊管理处", 12254222);
        map.put("深圳中海商城管理处", 12254510);
        map.put("石家庄国际会展中心", 12484346);
        map.put("福建外贸大厦管理处", 12614277);
        map.put("长春申通物流园管理处", 12544305);

        map.put("北京平安金融中心", 12554324);
        map.put("南通紫琅科技城", 12634266);
        map.put("太原中海国际中心管理处", 12254662);
        map.put("广州海珠米立方", 12664279);
        map.put("成都中海大厦管理处", 12244222);
        map.put("成都中海财富中心", 12254223);
        map.put("深圳敦煌大厦管理处", 12192461);
        map.put("深圳腾讯深汕管理处", 12254503);
        map.put("珠海富华里管理处", 12244647);

        map.put("佛山中海环宇城", 12284345);
        map.put("北京中建紫竹酒店管理处", 12254253);
        map.put("北京惠通时代广场管理处", 12254252);
        map.put("成都交子金融大街管理处", 12514309);
        map.put("杭州中海发展大厦", 12654267);
        map.put("汕头中信滨海新城管理处", 12254655);
        map.put("汕头南滨南路66号院物业管理处", 12514334);
        map.put("洛阳中移产业园管理处", 12254640);
        map.put("深圳世贸广场管理处", 12244415);
        map.put("苏州中海财富中心", 12244619);
        map.put("郑州河南中移在线管理处", 12494261);

        map.put("上海中建大厦管理处", 12254507);
        map.put("保定深圳园燕云城项目（前期项目）", 12464328);
        map.put("北京中海宏洋(中海国际中心)管理处", 12254212);
        map.put("北京中海置业大厦(中海地产广场)管理处", 12254213);
        map.put("南京环宇城管理处", 12254647);
        map.put("成都中建大厦管理处", 12244226);
        map.put("成都中海国际中心B区管理处", 12244224);
        map.put("深圳腾讯科兴物业管理处", 12254502);
        map.put("福建平安职场管理处", 12624261);
        map.put("郑州中建创业大厦管理处", 12244662);
        map.put("青岛中海大厦管理处", 12244764);
    }

    private static void organIdMapInit2(Map<String, Integer> map) {
        map.put("北京中海宏洋(中海国际中心)管理处", 12254212);
        map.put("北京奥南管理处", 12254254);
        map.put("华为海思项目", 12614267);
        map.put("天津中海财富中心管理处", 12244953);
        map.put("成都中海大厦管理处", 12244222);
        map.put("成都拓尔思天目中心管理处", 12464281);
        map.put("武汉腾讯项目", 12614261);
        map.put("沈阳国际中心大厦中海山姆会员店管理处", 12254243);
        map.put("沈阳国际中心大厦分公司国际中心管理处", 12254237);
        map.put("洛阳中移产业园管理处", 12254640);
        map.put("雄安市民中心", 12514313);
        map.put("上海国际中心管理处", 12244314);
    }

    private static void organIdMapInit3(Map<String, Integer> map){
        map.put("吉林中海悦江府管理处",12524287);
        map.put("吉林国际社区一区管理处",12264344);
        map.put("哈尔滨中海和院管理处",12544298);
        map.put("哈尔滨中海时代公馆管理处",12564278);
        map.put("大连中海云麓公馆管理处",12564281);
        map.put("成都中信城右岸管理处",12244405);
        map.put("成都中海云麓世家管理处",12454260);
        map.put("福州中海锦园管理处",12544317);
        map.put("长春中海盛世城B区管理处",12484307);
        map.put("长春中海盛世城CD区管理处",12464390);
        map.put("青岛中海红著管理处",12474286);

        map.put("厦门中海九号公馆管理处",12474309);
        map.put("厦门中海寰宇天下管理处",12244484);
        map.put("吉林中海国际社区一区第二管理处",12264345);
        map.put("吉林中海国际社区伴山郡管理处",12374266);
        map.put("吉林中海国际社区铂悦公馆东区管理处",12404266);
        map.put("吉林中海新都会管理处",12474260);
        map.put("吉林市国际社区三区管理处",12264346);
        map.put("杭州御道江河汇流公寓四区管理处",12254688);
        map.put("济南中海华山云著管理处",12534266);
        map.put("济南中海华山凯旋门管理处",12514290);
        map.put("福州中海锦城花园管理处",12464423);

        map.put("东莞凯旋国际管理处",12244527);
        map.put("吉林中海大厦管理处",12264352);
        map.put("吉林中海环宇天地管理处",12394265);
        map.put("吉林紫御江城管理处",12264349);
        map.put("哈尔滨中海时代管理处",12574262);
        map.put("成都中海物业管理有限公司昆明分公司",12644343);
        map.put("昆明中海寰宇花园管理处",12624269);
        map.put("青岛中海樘院管理处",12464292);
    }
}
