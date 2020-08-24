package unclassify;

//给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
//
//示例 1:
//
//输入: "abab"
//
//输出: True
//
//解释: 可由子字符串 "ab" 重复两次构成。
//示例 2:
//
//输入: "aba"
//
//输出: False
//示例 3:
//
//输入: "abcabcabcabc"
//
//输出: True
//
//解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/repeated-substring-pattern
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution15 {

//    public boolean repeatedSubstringPattern(String s) {
//        return (s + s).indexOf(s, 1) != s.length();
//    }

//    // overtime
//    public boolean repeatedSubstringPattern(String s) {
//        for(int i=0; i<s.length()-1; i++) {
//            if("".equals(s.replaceAll(s.substring(0, i+1), ""))) {
//                return true;
//            }
//        }
//        return false;
//    }

    // 8ms /40.2MB(利用 len%(i+1) == 0 条件过滤重复计算)
    // 174ms/40.2MB(不加 len%(i+1) == 0 条件)
    public boolean repeatedSubstringPattern(String s) {
        char[] cs = s.toCharArray();
        int len = s.length();
        // 0-i 范围字符所组成的字符串即为子串
        for(int i=0; i<len; i++) {
            if(len%(i+1) == 0) {
                // 判断i之后的字符所组成的字符串 是否能 被该子串 重复组成
                for(int j=i+1, k=0; j<len; j++) {
                    // 表示本次循环i对应的子串 无法组成 s
                    if(cs[j] != cs[k]) {
                        break;
                    }
                    // 本次循环对应i对应的子串 可以组成 s
                    if(j == len-1 && k == i) {
                        return true;
                    }
                    // 将子串当成环，指针到达边界时，重新从0开始判断
                    k = (k<i ? k+1 : 0);
                }
                // i遍历到len-1时，表名无重复子串，返回false
                if(i == len-1) {
                    return false;
                }
            }
        }
        return true;
    }

//    // 461ms/40.1MB
//    public boolean repeatedSubstringPattern(String s) {
//        for(int i=0; i<s.length(); i++) {
//            for(int j=i+1, k=0; j<s.length(); j++) {
//                if(s.charAt(j) != s.charAt(k)) {
//                    break;
//                }
//                if(j == s.length()-1 && k == i) {
//                    return true;
//                }
//                k = (k<i ? k+1 : 0);
//            }
//            if(i == s.length()-1) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static void main(String[] args) {
        System.out.println(new Solution15().repeatedSubstringPattern("abab")); //true
        System.out.println(new Solution15().repeatedSubstringPattern("aba"));  //false
        System.out.println(new Solution15().repeatedSubstringPattern("abcabcabcabc")); //true
        System.out.println(new Solution15().repeatedSubstringPattern("abcabcabcab")); //false
        System.out.println(new Solution15().repeatedSubstringPattern("bb")); //true
        System.out.println(new Solution15().repeatedSubstringPattern("zodwlwuxknpezmxxeksvyymlqyheptepjilxeiurxlvzasweerbcpnphnzmdssueahbhpvynivmiqioubqzqfqavpmaieumhbpqrkqgulsdbcryejzwrpwofioppkbylsoecekwrebvhvtleshxheloobdflgdxyrdcvobnsckbxdoexybgcnvsoaychbbfveksulhpnrqlwfptdsahfxbwxbyefbmafwsahcpwthjqmajrtlaykcwidwoixcifadfjfwgafrquscllpmlaoiktgacgdmlfpsrwozxvqppirbthphjfrtyxtadypogegxdwwasmpjxjmsqbzbgquhopjtqykwfqtippkjdbsakfvciudplzybphwfggblzwvqnfzqitttaoxjkbaxcyarfxynfzygxvwkaxdjtyetobeyeewheyamxzzdaajyvikyfnjalnubqaaxfyglwgxuhxzizkiussuaidavswxjxziezopvwnrgmsqndyjvsexahyyauxltwvxvlnshcmpqefdjdmhyqeaaeyczvnstksovfhehvuzzzclfqsdjwdjbelczawtmojhtslcnfdpffakysphqssrwfvyhsttgcacvngkvkzarbmpvbymvsfuxbsgvlzdpbfmroxmyyopachvfhjaapuzsognzhqrlwdekaqkzebbiiwnsgnsxktpybcajsrwquacxsmwyqzgaxtsfimcsgrthvtsqmqiislfkzdipcqqajkfuximdbhmxcfpoxxzqieckilbkdtmpesjbcxgdfucjbrazpzpzdrlnepyiikzoeirghxkmsoytgyuxxjycdmqhbqrjasyhapnkpzyjowewuzttitwnfmxgcqqejqllhbvwaufoqkkljfgtbchgqensufzdxmrenmdogiexurkfyqzzviglovgicfobrffhtivatbxnsjvrbwqweyisvocxvsyozgvtostjuszmdufeqybwwlqubsrwnskoyghoycyuwzjzvoelohjnszhttyrgsbvqjefkjfefgnhbenmsuvfowojppayhdvypbfzkmfsstztzmhtiebwapfrefpmkkmzmtyyfgqzzrsadztlfuhfmoyqtoegaqfolgnqmfpnxjnckiopdxwpmvhhlmplevcqbrinwyavjpyuxolankrbfzlsnafrvhjyyslxsnubcuxailcyvwzcvmuknzdkhnjhfwgxmbaovyqgjtggpfimucwhbztkoeutbasndtdztwhepnkguuuowsxztrmivgdyiwnmrtnmpwsgjemfyiwwatvvmjdkphiafymyrbkgxemiianikjekfbfrllbaumczkozdpllopzwzzkhlvnvaocuzpxcjjekvvjymujblixkjjtuhgrjvwdwlbyvmfhiargmnspbaplmahihpatkywjjzjgmoqwqhcfwuuxxlllmstvhvoutnffmonafdwvmbalxxiwivhupdqzygvutabgoqitufriirtfqeczzjvronqnuljnlmvbcgxylohthcaygcziouzckprgccvporqrzprmdafldhllkktygucqluynwucnntemkxyoyfbbmomwyiwgczzovxdrrgeebkqliobitcjgqxeqhbxkcyaxvdqplxtmhmarcbzwekewkknrnmdpmfohlfyweujlgjfikggqtmpfxewkqccehsluxmrbgcpmnwwzcdsxrhcolbbdddhmvuhvgaymcpyrlxqqainrpsictjfyomklhtgnvhcxwaqkxhazrpthjdlcmraadnnmiuaebedzeujedlfudadmdprpgrrlgfypbaveeyfkurqxcwshyxwcmntugngnugevgzbkzgwuxnrcllljjhlxufudsasmgwjoxkywsuqqjkuuxfeinpgjucmocgckclybxyyqsqieccychckwssiouwfydhihvgjuejmzbudeybgigseylmohjtgodovyxgubphcrbfxcjfkpxqpkfdsqzaecobcliwpxdrqhznjksvzslqeqtyisymwnoaawxrvcjmjkzuuuptodjsvetezrsxgiyjevacxjojtnfpjxizatuxandzewynewwlrwnribcdqhtcgdbgzvgbijapfaemxazqymjiplohvhjdmlmnisotbqijrmxoowgokqxzdlgauseifqynkmgldqwwetmsrsjbkgcotjqpduzimxxwkekpuinbeztvtczekbufmkwphwzvvxshbewxolysmuqgypmlqgsfspuznuxitpqwvotjcueoquuolibbquijlfkybhiuyaqvrlftflhvfemqmntdjcouhauiideifuewflrnwzkdfwttmoyvsmqbpklxpphvjntrynewmoztnsmzjnwbbmdqzopogxlpkhglumhibhdobrpmelogatwzjmppldhvmpigcqqdzttakknrxdxxytyjhozstiqfxynzqfepyzskjhbjmygkuftdvuwqcovcmkbdpguphmztvdchditxppbmjzconxqbjteybbnjppclseuvoovedwsoxvekuwleqscrtsckhevbkswzgrflpxzmsgwncktscxmxpohytfzfxyefplqhlrtdxssacnuglgfcvmbqtcvsaaxsnnzyhueeoygsmbyzhdbcseslhqgjsdbftceazkgningwqevxkxccbwpkmvuebxmuaswzccomrujhbehlblpfhgpwejafhmbjexkibyvvteeuinhmyyrggiekserbtskzruivsfsnjwjtkqfkqysxhwjbdbcwvoxpkhmcmesxcbrouyzkzrjiqtjtwnptboilwwpkahiflbtfbupaobieutxsiollcuyaqoltxvaiwtnzqfaegkicghuovfazbyauonpmipwgkmfathgbsqdyxzmugmuknugbhxprmmxyacbtkhueazexaquifouuiavrxnilbhkbllwxusburfutyodfcyzmyufyhpjiwtlhghhbzkxsqrnqhrvlpuzpqenavxkrqwbzdkafuzxmtncaezmttnimuwmpvyhnukmauohooedhtfhxfvsaldmfbauwzdjvpwvlkccyjfhckrcpouszzeeahbwgeibwezhqdjrmoyrzrvqgpkfrpmrypoetwhpcgzumilaxmtydoqjgbrxsfoquewpcdfgmgbaomanyulcienohelzjcrdgntlsarggsgwfhkfbxjlbxakdnjnrcsypnjkpaqxvxuagghjfruhqwttkdibfdbxqmhipzfkybmejtppmkycgryswgwejzbcmkkblrnktzqeugtjsrlajlvhsrldqmfeyrhkjwuevmisscbmvongaioubzxtofyowhyfeujbhuvbwwotndapnqjorihfnhumrurvpdyyxuzvspofuqwhihvaqfffmbrfwlanoblnvmipeyxyrtgzbdztihyxiscgdfvtgggdvquzorxflffojghrnhqybvggaczikhnfxheozetmjaashjmuseuwzbczdejnlwvlebzffurrvtleexawevsdyxfbbqqonnztpayuvrnskzyoyymcvhitenwffnvscvnrvlosebmqakgzltdgclcgayngniyqfojdzxvgbkwdudchygqatsiusvagfaylohoqhjwivulqxqzvypxrhfnaaniiqgtenhwwsueopsgokfmivjbeppgreozwuudkbziuwnklhqzrxnbsxezovfhcujyqdbchdldzankhflaczdxyhwodbxhxgfomhqvkbyldnvkuimwjpvjzrxevvlsdqirzzmlcmwgztstcfsnwnxvhuzcrkwxdfocgdqkpmsrilkmsezosykzvjdmublzgabofrzugrlqeycwcdlwcvvxgalcczbkbfzgyaagrowwoauqefnickhjnunptpbuuaaucdbutxshymxeummwxvhmpvbjmefihgcjlddedxlbkkhgxtcwqmifjlqvrwfyehirnnvqlitxuadtvhpntzscmlzejecnokgdadvlloihqnbnusaosusgfsmoyrdodjrdmmozcyhulijgajwueodlipchgfxkrpnfdeficocowmwyxuvdiyptptcyyzipoxxubcceqguossosmlvwlmpbfrzgrjxhgubkyfqomkegcvyqgwmukcvqtwymhvwpqxzodpfrblxfiyvruklemiwhguwnqscipeyuvdnxrlezlcsfmdlylihtedgwvlenrdjmizlbmqdxuvegzpemznggrocobjdsmphwouaakdxeybmzssmuguazkcnoltizipywkywwdvdbypdqirkqnkpxpelcxwchlcdxabwfwzgwqlnaczqpmfpbmcdjyhrmjwllyhqwiztbkibphsevztwlwcxwlqivmuimdzjmaenmoqbvigrkbpxztuyfkgaxckjgjuulajyozzmkhfwlutkqoivarxxthjbtfveggcwgeislnowkhjaxtxykuxgdihwofdwnhsvldnzsdxclgekabimvgsbqcxejacwkxsvchuuluxyabicfrpazjbpnbwwdavnxplbwhwjbthewqgbhxeijhzwkvvqysdxhqzfyvrzqydmomqxjwhsfhrqcogcglapczwaunbkmhyctocemjzsqskvgsxmdxdhwzhqplcbsbfnrhrersyjkieqaeedbhlajusyhvbbwgopqitjarslbzdbdihgrugaqzayubzskqjcydlawflaerdjzkqsgwtzvtzrxaljqvlqawrybcyivcrwohpxgkwbdvvcvglobweduvcyaagvezsvjhsprqswlnbiwmwzlddfyfdahlwonqjwzdlstfogtrorgnqrqvyqacrkppacflyeolwhrmnmgddmsvdirbbtfufvjhzosauglspegesmkxyctbnmfofkinhrujhnjdvuvnbbrtozodwlwuxknpezmxxeksvyymlqyheptepjilxeiurxlvzasweerbcpnphnzmdssueahbhpvynivmiqioubqzqfqavpmaieumhbpqrkqgulsdbcryejzwrpwofioppkbylsoecekwrebvhvtleshxheloobdflgdxyrdcvobnsckbxdoexybgcnvsoaychbbfveksulhpnrqlwfptdsahfxbwxbyefbmafwsahcpwthjqmajrtlaykcwidwoixcifadfjfwgafrquscllpmlaoiktgacgdmlfpsrwozxvqppirbthphjfrtyxtadypogegxdwwasmpjxjmsqbzbgquhopjtqykwfqtippkjdbsakfvciudplzybphwfggblzwvqnfzqitttaoxjkbaxcyarfxynfzygxvwkaxdjtyetobeyeewheyamxzzdaajyvikyfnjalnubqaaxfyglwgxuhxzizkiussuaidavswxjxziezopvwnrgmsqndyjvsexahyyauxltwvxvlnshcmpqefdjdmhyqeaaeyczvnstksovfhehvuzzzclfqsdjwdjbelczawtmojhtslcnfdpffakysphqssrwfvyhsttgcacvngkvkzarbmpvbymvsfuxbsgvlzdpbfmroxmyyopachvfhjaapuzsognzhqrlwdekaqkzebbiiwnsgnsxktpybcajsrwquacxsmwyqzgaxtsfimcsgrthvtsqmqiislfkzdipcqqajkfuximdbhmxcfpoxxzqieckilbkdtmpesjbcxgdfucjbrazpzpzdrlnepyiikzoeirghxkmsoytgyuxxjycdmqhbqrjasyhapnkpzyjowewuzttitwnfmxgcqqejqllhbvwaufoqkkljfgtbchgqensufzdxmrenmdogiexurkfyqzzviglovgicfobrffhtivatbxnsjvrbwqweyisvocxvsyozgvtostjuszmdufeqybwwlqubsrwnskoyghoycyuwzjzvoelohjnszhttyrgsbvqjefkjfefgnhbenmsuvfowojppayhdvypbfzkmfsstztzmhtiebwapfrefpmkkmzmtyyfgqzzrsadztlfuhfmoyqtoegaqfolgnqmfpnxjnckiopdxwpmvhhlmplevcqbrinwyavjpyuxolankrbfzlsnafrvhjyyslxsnubcuxailcyvwzcvmuknzdkhnjhfwgxmbaovyqgjtggpfimucwhbztkoeutbasndtdztwhepnkguuuowsxztrmivgdyiwnmrtnmpwsgjemfyiwwatvvmjdkphiafymyrbkgxemiianikjekfbfrllbaumczkozdpllopzwzzkhlvnvaocuzpxcjjekvvjymujblixkjjtuhgrjvwdwlbyvmfhiargmnspbaplmahihpatkywjjzjgmoqwqhcfwuuxxlllmstvhvoutnffmonafdwvmbalxxiwivhupdqzygvutabgoqitufriirtfqeczzjvronqnuljnlmvbcgxylohthcaygcziouzckprgccvporqrzprmdafldhllkktygucqluynwucnntemkxyoyfbbmomwyiwgczzovxdrrgeebkqliobitcjgqxeqhbxkcyaxvdqplxtmhmarcbzwekewkknrnmdpmfohlfyweujlgjfikggqtmpfxewkqccehsluxmrbgcpmnwwzcdsxrhcolbbdddhmvuhvgaymcpyrlxqqainrpsictjfyomklhtgnvhcxwaqkxhazrpthjdlcmraadnnmiuaebedzeujedlfudadmdprpgrrlgfypbaveeyfkurqxcwshyxwcmntugngnugevgzbkzgwuxnrcllljjhlxufudsasmgwjoxkywsuqqjkuuxfeinpgjucmocgckclybxyyqsqieccychckwssiouwfydhihvgjuejmzbudeybgigseylmohjtgodovyxgubphcrbfxcjfkpxqpkfdsqzaecobcliwpxdrqhznjksvzslqeqtyisymwnoaawxrvcjmjkzuuuptodjsvetezrsxgiyjevacxjojtnfpjxizatuxandzewynewwlrwnribcdqhtcgdbgzvgbijapfaemxazqymjiplohvhjdmlmnisotbqijrmxoowgokqxzdlgauseifqynkmgldqwwetmsrsjbkgcotjqpduzimxxwkekpuinbeztvtczekbufmkwphwzvvxshbewxolysmuqgypmlqgsfspuznuxitpqwvotjcueoquuolibbquijlfkybhiuyaqvrlftflhvfemqmntdjcouhauiideifuewflrnwzkdfwttmoyvsmqbpklxpphvjntrynewmoztnsmzjnwbbmdqzopogxlpkhglumhibhdobrpmelogatwzjmppldhvmpigcqqdzttakknrxdxxytyjhozstiqfxynzqfepyzskjhbjmygkuftdvuwqcovcmkbdpguphmztvdchditxppbmjzconxqbjteybbnjppclseuvoovedwsoxvekuwleqscrtsckhevbkswzgrflpxzmsgwncktscxmxpohytfzfxyefplqhlrtdxssacnuglgfcvmbqtcvsaaxsnnzyhueeoygsmbyzhdbcseslhqgjsdbftceazkgningwqevxkxccbwpkmvuebxmuaswzccomrujhbehlblpfhgpwejafhmbjexkibyvvteeuinhmyyrggiekserbtskzruivsfsnjwjtkqfkqysxhwjbdbcwvoxpkhmcmesxcbrouyzkzrjiqtjtwnptboilwwpkahiflbtfbupaobieutxsiollcuyaqoltxvaiwtnzqfaegkicghuovfazbyauonpmipwgkmfathgbsqdyxzmugmuknugbhxprmmxyacbtkhueazexaquifouuiavrxnilbhkbllwxusburfutyodfcyzmyufyhpjiwtlhghhbzkxsqrnqhrvlpuzpqenavxkrqwbzdkafuzxmtncaezmttnimuwmpvyhnukmauohooedhtfhxfvsaldmfbauwzdjvpwvlkccyjfhckrcpouszzeeahbwgeibwezhqdjrmoyrzrvqgpkfrpmrypoetwhpcgzumilaxmtydoqjgbrxsfoquewpcdfgmgbaomanyulcienohelzjcrdgntlsarggsgwfhkfbxjlbxakdnjnrcsypnjkpaqxvxuagghjfruhqwttkdibfdbxqmhipzfkybmejtppmkycgryswgwejzbcmkkblrnktzqeugtjsrlajlvhsrldqmfeyrhkjwuevmisscbmvongaioubzxtofyowhyfeujbhuvbwwotndapnqjorihfnhumrurvpdyyxuzvspofuqwhihvaqfffmbrfwlanoblnvmipeyxyrtgzbdztihyxiscgdfvtgggdvquzorxflffojghrnhqybvggaczikhnfxheozetmjaashjmuseuwzbczdejnlwvlebzffurrvtleexawevsdyxfbbqqonnztpayuvrnskzyoyymcvhitenwffnvscvnrvlosebmqakgzltdgclcgayngniyqfojdzxvgbkwdudchygqatsiusvagfaylohoqhjwivulqxqzvypxrhfnaaniiqgtenhwwsueopsgokfmivjbeppgreozwuudkbziuwnklhqzrxnbsxezovfhcujyqdbchdldzankhflaczdxyhwodbxhxgfomhqvkbyldnvkuimwjpvjzrxevvlsdqirzzmlcmwgztstcfsnwnxvhuzcrkwxdfocgdqkpmsrilkmsezosykzvjdmublzgabofrzugrlqeycwcdlwcvvxgalcczbkbfzgyaagrowwoauqefnickhjnunptpbuuaaucdbutxshymxeummwxvhmpvbjmefihgcjlddedxlbkkhgxtcwqmifjlqvrwfyehirnnvqlitxuadtvhpntzscmlzejecnokgdadvlloihqnbnusaosusgfsmoyrdodjrdmmozcyhulijgajwueodlipchgfxkrpnfdeficocowmwyxuvdiyptptcyyzipoxxubcceqguossosmlvwlmpbfrzgrjxhgubkyfqomkegcvyqgwmukcvqtwymhvwpqxzodpfrblxfiyvruklemiwhguwnqscipeyuvdnxrlezlcsfmdlylihtedgwvlenrdjmizlbmqdxuvegzpemznggrocobjdsmphwouaakdxeybmzssmuguazkcnoltizipywkywwdvdbypdqirkqnkpxpelcxwchlcdxabwfwzgwqlnaczqpmfpbmcdjyhrmjwllyhqwiztbkibphsevztwlwcxwlqivmuimdzjmaenmoqbvigrkbpxztuyfkgaxckjgjuulajyozzmkhfwlutkqoivarxxthjbtfveggcwgeislnowkhjaxtxykuxgdihwofdwnhsvldnzsdxclgekabimvgsbqcxejacwkxsvchuuluxyabicfrpazjbpnbwwdavnxplbwhwjbthewqgbhxeijhzwkvvqysdxhqzfyvrzqydmomqxjwhsfhrqcogcglapczwaunbkmhyctocemjzsqskvgsxmdxdhwzhqplcbsbfnrhrersyjkieqaeedbhlajusyhvbbwgopqitjarslbzdbdihgrugaqzayubzskqjcydlawflaerdjzkqsgwtzvtzrxaljqvlqawrybcyivcrwohpxgkwbdvvcvglobweduvcyaagvezsvjhsprqswlnbiwmwzlddfyfdahlwonqjwzdlstfogtrorgnqrqvyqacrkppacflyeolwhrmnmgddmsvdirbbtfufvjhzosauglspegesmkxyctbnmfofkinhrujhnjdvuvnbbrto"));
    }
}
