/**
 * <a href="http://www.openolat.org">
 * OpenOLAT - Online Learning and Training</a><br>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); <br>
 * you may not use this file except in compliance with the License.<br>
 * You may obtain a copy of the License at the
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">Apache homepage</a>
 * <p>
 * Unless required by applicable law or agreed to in writing,<br>
 * software distributed under the License is distributed on an "AS IS" BASIS, <br>
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. <br>
 * See the License for the specific language governing permissions and <br>
 * limitations under the License.
 * <p>
 * Initial code contributed and copyrighted by<br>
 * frentix GmbH, http://www.frentix.com
 * <p>
 */
package org.olat.selenium.page.qti;

import org.olat.selenium.page.graphene.OOGraphene;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 * Initial date: 03 may 2017<br>
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 *
 */
public class QTI21SingleChoiceEditorPage extends QTI21AssessmentItemEditorPage {

	public QTI21SingleChoiceEditorPage(WebDriver browser) {
		super(browser);
	}
	
	/**
	 * Add a new choice.
	 * 
	 * @return Itself
	 */
	public QTI21SingleChoiceEditorPage addChoice(int position) {
		By addBy = By.xpath("//div[contains(@class,'o_sel_add_choice_" + position + "')]/a");
		browser.findElement(addBy).click();
		OOGraphene.waitBusy(browser);
		//wait the next element
		By addedBy = By.xpath("//div[contains(@class,'o_sel_add_choice_" + (position + 1) + "')]/a");
		OOGraphene.waitElement(addedBy, 5, browser);
		return this;
	}
	
	public QTI21SingleChoiceEditorPage setCorrect(int position) {
		By correctBy = By.xpath("//div[contains(@class,'o_sel_choice_" + position + "')]//input[contains(@id,'oo_correct-')]");
		browser.findElement(correctBy).click();
		return this;
	}
	
	public QTI21SingleChoiceEditorPage setAnswer(int position, String answer) {
		String containerCssSelector = "div.o_sel_choice_" + position;
		OOGraphene.tinymce(answer, containerCssSelector, browser);
		return this;
	}
	
	public QTI21SingleChoiceEditorPage save() {
		By saveBy = By.cssSelector("fieldset.o_sel_choices_save button.btn.btn-primary");
		browser.findElement(saveBy).click();
		OOGraphene.waitBusy(browser);
		return this;
	}
	
	public QTI21ChoicesScoreEditorPage selectScores() {
		selectTab(By.className("o_sel_assessment_item_options"));
		return new QTI21ChoicesScoreEditorPage(browser);
	}

}