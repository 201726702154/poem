/**
 * 
 */
package com.example.dabuff.speechevaluation.ise.result;

import com.example.dabuff.speechevaluation.ise.result.util.ResultFormatUtil;

/**
 * <p>Title: ReadSentenceResult</p>
 * <p>Description: </p>
 * <p>Company: www.iflytek.com</p>
 * @author iflytek
 * @date 2015年1月12日 下午5:04:14
 */
public class ReadSentenceResult extends Result {
	
	public ReadSentenceResult() {
		category = "read_sentence";
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		if ("cn".equals(language)) {
			buffer.append("[结果分析]\n")
				.append("测试内容：" + content + "\n")
				.append("朗读时长：" + (time_len*1.0)/100+ "s" + "\n")
				.append("总分：" + total_score*20 + "分" + "\n\n");
			if(total_score<0.07)buffer.append("只击败了0.02%的用户，您还需多多努力哦！");
			else if(total_score>=0.07&&total_score<0.12)buffer.append("击败了0.65%的用户，您还需多多努力哦！");
			else if(total_score>=0.12&&total_score<0.17)buffer.append("击败了1.37%的用户，您还需多多努力哦！");
			else if(total_score>=0.17&&total_score<0.22)buffer.append("击败了2.39%的用户，您还需多多努力哦！");
			else if(total_score>=0.22&&total_score<0.27)buffer.append("击败了4.56%的用户，您还需多多努力哦！");
			else if(total_score>=0.27&&total_score<0.32)buffer.append("击败了5.00%的用户，您还需多多努力哦！");
			else if(total_score>=0.32&&total_score<0.37)buffer.append("击败了6.11%的用户，您还需多多努力哦！");
			else if(total_score>=0.37&&total_score<0.42)buffer.append("击败了7.42%的用户，您还需多多努力哦！");
			else if(total_score>=0.42&&total_score<0.47)buffer.append("击败了8.55%的用户，您还需多多努力哦！");
			else if(total_score>=0.47&&total_score<0.52)buffer.append("击败了9.66%的用户，您还需多多努力哦！");
			else if(total_score>=0.52&&total_score<0.57)buffer.append("恭喜您，击败了10.74%的用户，距离诗人境界近了一步！");
			else if(total_score>=0.57&&total_score<0.62)buffer.append("恭喜您，击败了11.23%的用户，距离诗人境界近了一步！");
			else if(total_score>=0.62&&total_score<0.67)buffer.append("恭喜您，击败了12.84%的用户，距离诗人境界近了一步！");
			else if(total_score>=0.67&&total_score<0.72)buffer.append("恭喜您，击败了13.04%的用户，距离诗人境界近了一步！");
			else if(total_score>=0.72&&total_score<0.77)buffer.append("恭喜您，击败了14.31%的用户，距离诗人境界近了一步！");
			else if(total_score>=0.77&&total_score<0.82)buffer.append("恭喜您，击败了15.66%的用户，距离诗人境界近了一步！");
			else if(total_score>=0.82&&total_score<0.87)buffer.append("恭喜您，击败了16.78%的用户，距离诗人境界近了一步！");
			else if(total_score>=0.87&&total_score<0.92)buffer.append("恭喜您，击败了17.36%的用户，距离诗人境界近了一步！");
			else if(total_score>=0.92&&total_score<0.97)buffer.append("恭喜您，击败了18.95%的用户，距离诗人境界近了一步！");
			else if(total_score>=0.97&&total_score<1.02)buffer.append("恭喜您，击败了19.74%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.02&&total_score<1.07)buffer.append("恭喜您，击败了20.48%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.07&&total_score<1.12)buffer.append("恭喜您，击败了21.22%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.12&&total_score<1.17)buffer.append("恭喜您，击败了23.54%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.17&&total_score<1.22)buffer.append("恭喜您，击败了24.32%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.22&&total_score<1.27)buffer.append("恭喜您，击败了25.84%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.27&&total_score<1.32)buffer.append("恭喜您，击败了26.11%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.32&&total_score<1.37)buffer.append("恭喜您，击败了27.35%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.37&&total_score<1.42)buffer.append("恭喜您，击败了28.19%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.42&&total_score<1.47)buffer.append("恭喜您，击败了29.45%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.47&&total_score<1.52)buffer.append("恭喜您，击败了30.41%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.52&&total_score<1.57)buffer.append("恭喜您，击败了31.22%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.57&&total_score<1.62)buffer.append("恭喜您，击败了32.77%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.62&&total_score<1.67)buffer.append("恭喜您，击败了33.44%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.67&&total_score<1.72)buffer.append("恭喜您，击败了34.55%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.72&&total_score<1.77)buffer.append("恭喜您，击败了35.87%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.77&&total_score<1.82)buffer.append("恭喜您，击败了36.36%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.82&&total_score<1.87)buffer.append("恭喜您，击败了37.89%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.87&&total_score<1.92)buffer.append("恭喜您，击败了38.65%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.92&&total_score<1.97)buffer.append("恭喜您，击败了39.73%的用户，距离诗人境界近了一步！");
			else if(total_score>=1.97&&total_score<2.02)buffer.append("恭喜您，击败了40.10%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.02&&total_score<2.07)buffer.append("恭喜您，击败了41.23%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.07&&total_score<2.12)buffer.append("恭喜您，击败了42.65%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.12&&total_score<2.17)buffer.append("恭喜您，击败了43.47%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.17&&total_score<2.22)buffer.append("恭喜您，击败了44.10%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.22&&total_score<2.27)buffer.append("恭喜您，击败了45.88%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.27&&total_score<2.32)buffer.append("恭喜您，击败了46.37%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.32&&total_score<2.37)buffer.append("恭喜您，击败了47.99%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.37&&total_score<2.42)buffer.append("恭喜您，击败了48.52%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.42&&total_score<2.47)buffer.append("恭喜您，击败了49.62%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.47&&total_score<2.52)buffer.append("恭喜您，击败了50.33%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.52&&total_score<2.57)buffer.append("恭喜您，击败了51.74%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.57&&total_score<2.62)buffer.append("恭喜您，击败了52.64%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.62&&total_score<2.67)buffer.append("恭喜您，击败了53.71%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.67&&total_score<2.72)buffer.append("恭喜您，击败了54.68%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.72&&total_score<2.77)buffer.append("恭喜您，击败了55.65%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.77&&total_score<2.82)buffer.append("恭喜您，击败了56.46%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.82&&total_score<2.87)buffer.append("恭喜您，击败了57.92%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.87&&total_score<2.92)buffer.append("恭喜您，击败了58.30%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.92&&total_score<2.97)buffer.append("恭喜您，击败了59.48%的用户，距离诗人境界近了一步！");
			else if(total_score>=2.97&&total_score<3.02)buffer.append("恭喜您，击败了59.13%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.02&&total_score<3.07)buffer.append("恭喜您，击败了60.44%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.07&&total_score<3.12)buffer.append("恭喜您，击败了61.49%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.12&&total_score<3.17)buffer.append("恭喜您，击败了62.23%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.17&&total_score<3.22)buffer.append("恭喜您，击败了63.73%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.22&&total_score<3.27)buffer.append("恭喜您，击败了64.85%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.27&&total_score<3.32)buffer.append("恭喜您，击败了65.73%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.32&&total_score<3.37)buffer.append("恭喜您，击败了66.03%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.37&&total_score<3.42)buffer.append("恭喜您，击败了67.84%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.42&&total_score<3.47)buffer.append("恭喜您，击败了68.37%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.47&&total_score<3.52)buffer.append("恭喜您，击败了69.63%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.52&&total_score<3.57)buffer.append("恭喜您，击败了70.03%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.57&&total_score<3.62)buffer.append("恭喜您，击败了71.45%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.62&&total_score<3.67)buffer.append("恭喜您，击败了72.64%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.67&&total_score<3.72)buffer.append("恭喜您，击败了73.96%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.72&&total_score<3.77)buffer.append("恭喜您，击败了74.88%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.77&&total_score<3.82)buffer.append("恭喜您，击败了75.66%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.82&&total_score<3.87)buffer.append("恭喜您，击败了76.01%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.87&&total_score<3.92)buffer.append("恭喜您，击败了77.44%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.92&&total_score<3.97)buffer.append("恭喜您，击败了78.87%的用户，距离诗人境界近了一步！");
			else if(total_score>=3.97&&total_score<4.02)buffer.append("恭喜您，击败了79.64%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.02&&total_score<4.07)buffer.append("恭喜您，击败了80.13%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.07&&total_score<4.12)buffer.append("恭喜您，击败了81.15%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.12&&total_score<4.17)buffer.append("恭喜您，击败了82.65%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.17&&total_score<4.22)buffer.append("恭喜您，击败了83.77%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.22&&total_score<4.27)buffer.append("恭喜您，击败了84.09%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.27&&total_score<4.32)buffer.append("恭喜您，击败了85.94%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.32&&total_score<4.37)buffer.append("恭喜您，击败了86.34%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.37&&total_score<4.42)buffer.append("恭喜您，击败了87.88%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.42&&total_score<4.47)buffer.append("恭喜您，击败了88.20%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.47&&total_score<4.52)buffer.append("恭喜您，击败了89.64%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.52&&total_score<4.57)buffer.append("恭喜您，击败了90.23%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.57&&total_score<4.62)buffer.append("恭喜您，击败了91.56%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.62&&total_score<4.67)buffer.append("恭喜您，击败了92.77%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.67&&total_score<4.72)buffer.append("恭喜您，击败了93.46%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.72&&total_score<4.77)buffer.append("恭喜您，击败了94.60%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.77&&total_score<4.82)buffer.append("恭喜您，击败了95.55%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.82&&total_score<4.87)buffer.append("恭喜您，击败了96.33%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.87&&total_score<4.92)buffer.append("恭喜您，击败了97.65%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.92&&total_score<4.97)buffer.append("恭喜您，击败了98.64%的用户，距离诗人境界近了一步！");
			else if(total_score>=4.97&&total_score<5.00)buffer.append("恭喜您，击败了99.03%的用户，距离诗人境界近了一步！");
			else if(total_score>=5.00)buffer.append("恭喜您，击败了99.99%的用户，距离诗人境界近了一步！");
//				.append("[朗读详情]").append(ResultFormatUtil.formatDetails_CN(sentences));
		} else {
			if (is_rejected) {
				buffer.append("检测到乱读，")
					.append("except_info:" + except_info + "\n\n");	// except_info代码说明详见《语音评测参数、结果说明文档》
			}
			
			buffer.append("[结果分析]\n")
				.append("测试内容：" + content + "\n")
				.append("总分：" + total_score*20 + "\n\n");
//				.append("[朗读详情]").append(ResultFormatUtil.formatDetails_EN(sentences));
		}
		
		return buffer.toString();
	}
}
