package com.khopa.core.views.widgets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.khopa.core.services.skin.SkinService;
import com.khopa.core.views.Utils;

public class TextZone extends Table {

	/**
	 * Text content
	 */
	protected String text = null;

	/**
	 * Font
	 */
	protected BitmapFont font = null;
	
	/**
	 * Maximum size of the zone in pixels
	 */
	protected Vector2 maxSize= null;
	
	/**
	 * Labels
	 */
	private List<Label> labels;
	
	/**
	 * Words
	 */
	private List<String> words;
	
	public TextZone(){
		
	}
	
	public TextZone(String text, Skin skin, Vector2 maxSize){
		super(skin);
		this.text = text;
		this.font = skin.getFont("default-font");
		this.maxSize = new Vector2(maxSize.x,
								   maxSize.y);
		this.labels = new ArrayList<Label>();
		this.words = new ArrayList<String>();
		//this.debug();
		build();
	}
	
	public void build(){
		
		String[] words = this.text.split(" ");
		List<String[]> lines = new ArrayList<String[]>();
		
		/*for(String w:words){
			System.out.println(w);
		}*/
		
		int w = 0; // Word index
		while(w < words.length){
			int startLine = w;
			while(true){
				String [] lineWords = Arrays.copyOfRange(words, startLine, ++w);
				String line = Utils.strJoin(lineWords, " ");
				
				if(font.getBounds(line).width > maxSize.x){
					lines.add(Arrays.copyOfRange(lineWords, 0, w-startLine-1));
					w--;
					break;
				}else if(w >= words.length){
					lines.add(Arrays.copyOfRange(lineWords, 0, w-startLine));
					break;
				}
			}
		}	
		
		float spacing = 0;
		boolean isLastLine = false;
		int lineIndex = 0;
		for(String[] line:lines){
			
			isLastLine = (lineIndex == lines.size()-1);
			lineIndex++;
			
			if(line.length > 1 && !isLastLine){
				spacing = (maxSize.x - font.getBounds(Utils.strJoin(line, " ")).width)/(line.length-1);
			}
			else{
				spacing = 0;
			}
			Table lineTable = new Table(SkinService.getSkin());
			int i = 0;
			for(String word:line){
				Label lb;
				String fWord;
				if(i == 0){
					fWord = word + " "; 
					lb = new Label(fWord, SkinService.getSkin());
					lineTable.add(lb).spaceRight(spacing).spaceLeft(0);
				}
				else if(i == line.length-1){
					fWord = word;
					lb = new Label(fWord, SkinService.getSkin());
					lineTable.add(lb).spaceLeft(0).spaceLeft(spacing);
				}
				else{
					fWord = word + " "; 
					lb = new Label(fWord, SkinService.getSkin());
					lineTable.add(lb).spaceLeft(spacing).spaceLeft(spacing);
				}
				this.labels.add(lb);
				this.words.add(fWord);
				i++;
			}
			lineTable.debug();
			this.add(lineTable).align(Align.left);
			this.row();
		}

	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		this.clearChildren();
		this.build();
	}

	public Vector2 getMaxSize() {
		return maxSize;
	} 
	
	
	@Override
	public void clearChildren() {
		super.clearChildren();
		this.labels.clear();
		this.words.clear();
	}

	public List<Label> getLabels() {
		return labels;
	}

	public List<String> getWords() {
		return words;
	}
}
