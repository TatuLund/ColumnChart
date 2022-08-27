import { css, html, LitElement } from 'lit-element';
import { customElement, property } from 'lit/decorators.js';

interface Value {
	number : number;
	caption : string;
}

@customElement('column-chart')
export class LabeledText extends LitElement {

  @property()
  values : Value[] = [];

  @property({reflect: true})
  colored : boolean = false;

  @property({reflect: true})
  lines : boolean = true;

  color = 0;

  static get styles() {
    return css`
        :host {
            --column-chart-color-0: var(--vaadin-charts-color-0, #E6C317);
            --column-chart-color-1: var(--vaadin-charts-color-1, #7997F2);
            --column-chart-color-2: var(--vaadin-charts-color-2, #C2F261);
            --column-chart-color-3: var(--vaadin-charts-color-3, #D96C57);
            --column-chart-color-4: var(--vaadin-charts-color-4, #5C81CC);
            --column-chart-color-5: var(--vaadin-charts-color-5, #39BF87);
            --column-chart-color-6: var(--vaadin-charts-color-6, #E695AC);
            --column-chart-color-7: var(--vaadin-charts-color-7, #75D0FF);
            --column-chart-color-8: var(--vaadin-charts-color-8, #904FB0);
            --column-chart-color-9: var(--vaadin-charts-color-9, #A6EDD9);
            --column-chart-container-width: var(--column-chart-width, 500px);
        }
		.segment {
            writing-mode: vertical-lr;
			overflow: hidden;
			text-align: right;
            box-shadow: var(--lumo-box-shadow-m);
		    margin-left: var(--lumo-space-xs);
    		margin-right: var(--lumo-space-xs);
		}
		:host([colored='false']) .segment {
			color: var(--lumo-primary-contrast-color);			
		}
		:host([colored='true']) .segment {
			color: var(--lumo-secondary-text-color);
		}
		:host([lines='true']) .container {
            background-image: linear-gradient(0deg, var(--lumo-contrast-30pct) 1%, transparent 1%, transparent 50%, var(--lumo-contrast-30pct) 50%, var(--lumo-contrast-30pct) 51%, transparent 51%, transparent 100%);
            background-size: 100% 9.9%;
            background-repeat: space;			
		}
		.container {
			display: flex;
			flex-direction: row;
			align-items: flex-end;
			width: var(--column-chart-container-width, 500px);
			height: var(--column-chart-height, 300px);
            border-radius: var(--lumo-border-radius-m);
            box-shadow: 0 0 0 1px var(--lumo-contrast-30pct);
		}
		:host([leftaxis]) .left {
			display: flex;
		}
		.axis {
			display: none;
			flex-direction: column;
			height: 100%;
			width: 0%;
			font-size: var(--lumo-font-size-xs);
            color: var(--lumo-body-text-color);
			text-shadow: 1px 1px 5px var(--lumo-shade-90pct);
			z-index: 1;
		}
		.bottom {
			height: 50%;
			display: flex;
			align-items: flex-end;
		}
		:host([rightaxis]) .right {
			display: flex;
		}
    `;
  }

  _getStylesForValue(value : number) : string {
	var max = 0;
	this.values.forEach(v => {
		if (v.number > max) {
			max=v.number;
		}
	});
	var min = max;
	this.values.forEach(v => {
		if (v.number < min) {
			min=v.number;
		}
	});
	var range : number;
	if (min<0) {
	    range=max+Math.abs(min);
	} else {
		range=max;
	}
	var style : string;
	if (value > 0) {
		style = "position: relative; bottom: "+100*Math.abs(Math.min(0,min))/range+"%;";
	} else {
		style = "position: relative; bottom: "+100*Math.abs(Math.abs(min)+value)/range+"%;";			
	}
	style+=" font-size: calc(var(--column-chart-container-width)/"+this.values.length*2+"); width: "+100/this.values.length+"%; height: "+100*Math.abs(value)/range+"%;"
	if (this.colored) {
	   style+="background: var(--column-chart-color-"+this.color+");";
    } else {
	   style+="background: var(--lumo-primary-color);";	
    }
    if (value > 0) {
       style+="border-top-right-radius: var(--lumo-border-radius-m); border-top-left-radius: var(--lumo-border-radius-m)";
    } else {
       style+="border-bottom-right-radius: var(--lumo-border-radius-m); border-bottom-left-radius: var(--lumo-border-radius-m); text-align: left";	
    }
	this.color = (this.color + 1) % 10;
	return style;
  }

  _getMax() : string {
	var max = 0;
	this.values.forEach(v => {
		if (v.number > max) {
			max=v.number;
		}
	});
	return max.toFixed(2);
  }

  _getMin() : string {
	var max = 0;
	this.values.forEach(v => {
		if (v.number > max) {
			max=v.number;
		}
	});
	var min = max;
	this.values.forEach(v => {
		if (v.number < min) {
			min=v.number;
		}
	});
	if (min > 0) {
		return "0";
	} else {
		return min.toFixed(2);		
	}
  }

  _getMiddle() : string {
	var max = 0;
	this.values.forEach(v => {
		if (v.number > max) {
			max=v.number;
		}
	});
	var min = max;
	this.values.forEach(v => {
		if (v.number < min) {
			min=v.number;
		}
	});
	var middle : number;
	if (min < 0) {
		middle = Math.min(0,min)+(max + Math.abs(min))/2;
	} else {
		middle = max / 2;
	}
	return middle.toFixed(2);
  }

  render() {
    return html`
		<div class="container">
			<div class='axis left'><span style='height: 50%'>${this._getMax()}</span><span>${this._getMiddle()}</span><span class='bottom'>${this._getMin()}</span></div>
            ${this.values.map((value) => html`
			    <div .title='${value.caption}' .style='${this._getStylesForValue(value.number)}' class='segment'>${value.caption}</div>
			`)}
			<div class='axis right'><span style='height: 50%'>${this._getMax()}</span><span>${this._getMiddle()}</span><span class='bottom'>${this._getMin()}</span></div>
		</div>
    `;
  }

}
