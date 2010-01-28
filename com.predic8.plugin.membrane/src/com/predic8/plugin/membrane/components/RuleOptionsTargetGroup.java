/* Copyright 2009 predic8 GmbH, www.predic8.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package com.predic8.plugin.membrane.components;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.predic8.membrane.core.Router;

public class RuleOptionsTargetGroup {

	private Text textTargetPort;

	private Text textTargetHost;

	public RuleOptionsTargetGroup(Composite parent, int style) {
		Group group = createGroup(parent, style);

		new Label(group, SWT.NONE).setText("Host");

		textTargetHost = createTargetHostText(group);

		createDummyLabel(group).setText(" ");
		
		createDummyLabel(group).setText(" ");
		
		new Label(group, SWT.NONE).setText("Port");

		textTargetPort = createTargetPortText(group);
		
		createDummyLabel(group).setText(" ");
		createDummyLabel(group).setText(" ");
		
	}

	private Text createTargetHostText(Group group) {
		Text textTargetHost = new Text(group, SWT.BORDER);
		textTargetHost.setText(Router.getInstance().getRuleManager().getDefaultTargetHost());
		textTargetHost.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e) {
				//RuleOptionsTargetGroup.this.parent.setEnableOnlyModifyAndRestoreButton(true);
			}});
		textTargetHost.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return textTargetHost;
	}

	private Text createTargetPortText(Group group) {
		Text text = new Text(group,SWT.BORDER);
		text.setText(Router.getInstance().getRuleManager().getDefaultTargetPort());
		text.addVerifyListener(new PortVerifyListener());
		text.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e) {
				//RuleOptionsTargetGroup.this.parent.setEnableOnlyModifyAndRestoreButton(true);
			}});
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return text;
	}

	private Group createGroup(Composite parent, int style) {
		Group group = new Group(parent, style);
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		GridLayout gridLayout4TargetGroup = new GridLayout();
		gridLayout4TargetGroup.numColumns = 4;
		group.setLayout(gridLayout4TargetGroup);
		return group;
	}

	private Label createDummyLabel(Composite parent) {
		Label lb = new Label(parent, SWT.NONE);
		GridData gData4Dummy4 = new GridData(GridData.FILL_HORIZONTAL);
		lb.setLayoutData(gData4Dummy4);
		return lb;
	}

	public void clear() {
		textTargetHost.setText("");
		textTargetPort.setText("");
	}

	public String getTargetHost() {
		return textTargetHost.getText().trim();
	}

	public String getTargetPort() {
		return textTargetPort.getText().trim();
	}

	public void setTargetHost(String host) {
		textTargetHost.setText(host);
	}

	public void setTargetPort(String port) {
		textTargetPort.setText(port);
	}

	public void setTargetPort(int port) {
		textTargetPort.setText(Integer.toString(port));
	}
	
}