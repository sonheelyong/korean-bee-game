package com.project.side.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CheckService {
	
	 	private static final char[] CHO = {'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};
	    private static final char[] JUNG = {'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'};
	    private static final char[] JONG = {' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};

	public String checkAnswer(List<Character> spellA, List<Character> spellB, String answer) {
		
		String request = "";
		
		System.out.println("자음 : " + spellA + "모음 : " + spellB + "답변 : " + answer);
		 // 주어진 자음과 모음
        List<Character> allowedConsonants = spellA;
        List<Character> allowedVowels = spellB;

        // 입력 문자열이 주어진 자음과 모음 조건에 맞는지 확인
        for (char ch : answer.toCharArray()) {
            if (isHangulSyllable(ch)) {
                int[] decomposition = decomposeHangul(ch);
                char cho = CHO[decomposition[0]];
                char jung = JUNG[decomposition[1]];
                char jong = JONG[decomposition[2]];

                if (isValidConsonant(cho, jong, allowedConsonants) && isValidVowel(jung, allowedVowels)) {
                    System.out.printf("허용된 문자입니다.", ch);
                    request = "0";
                } else {
                	request = "1";
                    System.out.printf("허용되지 않은 문자입니다.", ch);
                }
            } else {
            	request = "2";
                System.out.printf("한글이 아닙니다.", ch);
            }
        }
		return answer;

	
	}
	
	 // 한글 음절을 초성, 중성, 종성으로 분해하는 메서드
    public static int[] decomposeHangul(char syllable) {
        int unicode = syllable - 0xAC00;
        int cho = unicode / (21 * 28);
        int jung = (unicode % (21 * 28)) / 28;
        int jong = unicode % 28;
        return new int[]{cho, jung, jong};
    }

    // 한글 음절인지 확인하는 메서드
    public static boolean isHangulSyllable(char ch) {
        return (ch >= 0xAC00 && ch <= 0xD7A3);
    }

    // 자음 유효성 검사 (자음 조합 포함)
    public static boolean isValidConsonant(char cho, char jong, List<Character> allowedConsonants) {
 
    	// 허용된 쌍자음(예: ㄱ = ㄱ + ㄱ)
    	if (cho == 'ㄲ' && allowedConsonants.contains('ㄱ')) {
            return true;
        }
        
        if (cho == 'ㄸ' && allowedConsonants.contains('ㄷ')) {
            return true;
        }
        
        if (cho == 'ㅃ' && allowedConsonants.contains('ㅂ')) {
            return true;
        }
        
        if (cho == 'ㅆ' && allowedConsonants.contains('ㅅ')) {
            return true;
        }
        
        if (cho == 'ㅉ' && allowedConsonants.contains('ㅈ')) {
            return true;
        }
    	
        // 초성이 허용된 자음인지 확인
        if (!allowedConsonants.contains(cho)) {
            return false;
        }
        
        
        // 종성이 허용된 쌍자음(예: ㄱ = ㄱ + ㄱ)
        if (jong == 'ㄲ' && allowedConsonants.contains('ㄱ')) {
            return true;
        }
        
        if (jong == 'ㄸ' && allowedConsonants.contains('ㄷ')) {
            return true;
        }
        
        if (jong == 'ㅃ' && allowedConsonants.contains('ㅂ')) {
            return true;
        }
        
        if (jong == 'ㅆ' && allowedConsonants.contains('ㅅ')) {
            return true;
        }
        
        if (jong == 'ㅉ' && allowedConsonants.contains('ㅈ')) {
            return true;
        }
        
        // 종성이 허용된 자음 조합인지 확인 (예: ㄱ + ㅅ = ㄳ 허용)
        
        if (jong == 'ㄳ' && allowedConsonants.contains('ㄱ') && allowedConsonants.contains('ㅅ')) {
            return true;
        }
        
        if (jong == 'ㄵ' && allowedConsonants.contains('ㄴ') && allowedConsonants.contains('ㅈ')) {
            return true;
        }
        
        if (jong == 'ㄶ' && allowedConsonants.contains('ㄴ') && allowedConsonants.contains('ㅎ')) {
            return true;
        }
        
        if (jong == 'ㄺ' && allowedConsonants.contains('ㄹ') && allowedConsonants.contains('ㄱ')) {
            return true;
        }
        
        if (jong == 'ㄻ' && allowedConsonants.contains('ㄹ') && allowedConsonants.contains('ㅁ')) {
            return true;
        }
        
        if (jong == 'ㄼ' && allowedConsonants.contains('ㄹ') && allowedConsonants.contains('ㅂ')) {
            return true;
        }
        
        if (jong == 'ㄽ' && allowedConsonants.contains('ㄹ') && allowedConsonants.contains('ㅅ')) {
            return true;
        }
        
        if (jong == 'ㄾ' && allowedConsonants.contains('ㄹ') && allowedConsonants.contains('ㅌ')) {
            return true;
        }
        
        if (jong == 'ㄿ' && allowedConsonants.contains('ㄹ') && allowedConsonants.contains('ㅍ')) {
            return true;
        }
        
        if (jong == 'ㅀ' && allowedConsonants.contains('ㄹ') && allowedConsonants.contains('ㅎ')) {
            return true;
        }
        
        if (jong == 'ㅄ' && allowedConsonants.contains('ㅂ') && allowedConsonants.contains('ㅅ')) {
            return true;
        }
        
        // 종성이 없다면 유효한 것으로 간주
        return jong == ' ' || allowedConsonants.contains(jong);
    }

    // 모음 유효성 검사 (모음 조합 포함)
    public static boolean isValidVowel(char jung, List<Character> allowedVowels) {
        // 단순 모음 포함 여부 확인
        if (allowedVowels.contains(jung)) {
            return true;
        }

        // 모음 조합 허용 규칙
        Map<List<Character>, Character> vowelCombinations = new HashMap<>();
        vowelCombinations.put(Arrays.asList('ㅓ', 'ㅣ'), 'ㅔ');
        vowelCombinations.put(Arrays.asList('ㅕ', 'ㅣ'), 'ㅖ');
        vowelCombinations.put(Arrays.asList('ㅏ', 'ㅣ'), 'ㅐ');
        vowelCombinations.put(Arrays.asList('ㅑ', 'ㅣ'), 'ㅒ');
        vowelCombinations.put(Arrays.asList('ㅗ', 'ㅏ'), 'ㅘ');
        vowelCombinations.put(Arrays.asList('ㅗ', 'ㅐ'), 'ㅙ');
        vowelCombinations.put(Arrays.asList('ㅗ', 'ㅣ'), 'ㅚ');
        vowelCombinations.put(Arrays.asList('ㅜ', 'ㅓ'), 'ㅝ');
        vowelCombinations.put(Arrays.asList('ㅜ', 'ㅔ'), 'ㅞ');
        vowelCombinations.put(Arrays.asList('ㅜ', 'ㅣ'), 'ㅟ');
        vowelCombinations.put(Arrays.asList('ㅡ', 'ㅣ'), 'ㅢ');

        // 주어진 모음 조합이 허용되는지 확인
        for (Map.Entry<List<Character>, Character> entry : vowelCombinations.entrySet()) {
            List<Character> combination = entry.getKey();
            char combinedVowel = entry.getValue();
            if (jung == combinedVowel && allowedVowels.containsAll(combination)) {
                return true;
            }
        }

        return false;
    }
	

}
