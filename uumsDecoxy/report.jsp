<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报表统计页面</title>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function searchByDate() {
		if (document.getElementById("startDate").value == "") {
			alert("请输入起始日期！");
			document.getElementById("startDate").focus();
		} else {
			document.forms["frm"].action = "/uums/reportByDate.do";
			document.forms["frm"].submit();
		}
	}

	function searchByYear() {
		if (document.getElementById("reportYear").value == "") {
			alert("请输入查询年份！");
			document.getElementById("reportYear").focus();
		} else {
			document.forms["frm"].action = "/uums/reportByMonth.do";
			document.forms["frm"].submit();
		}
	}

	function reSet() {
		document.forms["frm"].action = "/uums/resetReport.do";
		document.forms["frm"].submit();
	}

	function toBack() {
		document.forms["frm"].action = "/uums/index.do";
		document.forms["frm"].submit();
	}
</script>
</head>
<body>
<s:form method="post" name="frm" theme="simple">
	<table width="900" align="left">
		<tr>
			<td>
			<table width="900" align="left">
				<tr>
					<td align="left" colspan="5" height="50"><input type="button"
						name="back" value="返回" onClick="toBack()"></td>
				</tr>
				<tr>
					<td colspan="5"><strong>所有站点最近7天注册用户数量：&nbsp;</strong><s:property
						value="lastfor7" /></td>
				</tr>
				<tr>
					<td colspan="5"><strong>所有站点最近30天注册用户数量：</strong><s:property
						value="lastfor30" /></td>
				</tr>
				<tr height="20">
					<td colspan="5">
					<hr color="green">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="900" align="left">
				<tr>
					<td nowrap="nowrap">起始日期</td>
					<td><input type="text" class="Wdate"
						id="startDate" readOnly name="startDate"
						value="<s:property value="startDate" />"
						onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'endDate\')||\'%y-%M-%d\'}'})"
						tabindex="1"
						onkeydown="javascript:document.getElementById('endDate').focus()" /></td>
					<td nowrap="nowrap">结束日期</td>
					<td><input type="text" class="Wdate" id="endDate"
						readOnly name="endDate" value="<s:property value="endDate" />"
						onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'%y-%M-%d'})"
						tabindex="2"
						onkeydown="javascript:document.getElementById('site').focus()" /></td>
					<td nowrap="nowrap"><input type="button" id="searchButton1"
						name="searchButton1" tabindex="4" value="查询"
						onClick="searchByDate()">&nbsp;&nbsp;<input type="reset"
						name="reset" value="重置" onclick="reSet()" tabindex="5"></td>
				</tr>
				<tr height="30">
					<td colspan="5"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="900" align="left">
				<tr>
					<td width="40%">
					<table>
						<s:iterator value="urlList" status="stat">
							<tr>
								<td align=left>
								<s:set name="statusFlg" value="%{status}"></s:set>
								<s:if test='#statusFlg == "checked"'>
								<input type="checkbox" name="site" value="<s:property value="key"/>" checked/><s:property value="value" />
								</s:if>
								<s:else>
								<input type="checkbox" name="site" value="<s:property value="key"/>"/><s:property value="value" />
								</s:else>
								</td>
								</tr>
						</s:iterator>
					</table>
					</td>
					<td width="60%"><s:set name="report1Flg" value="%{report1Flg}"></s:set> <s:if
						test="#report1Flg == 1">
						<table width="500" border="1" align="left" cellpadding="1"
							cellspacing="0">
							<tr>
								<td align="center" width="70%" colspan="4"><strong>站点</strong></td>
								<td align="center" width="30%" colspan="3"><strong>注册数量</strong></td>
							</tr>
							<s:iterator status="st" value="report1List">
								<tr>
									<td nowrap colspan="4" align="left"><s:property
										value="register_source" /></td>
									<td nowrap colspan="3" align="right"><s:property
										value="regNum" /></td>
								</tr>
							</s:iterator>
							<tr>
								<td align="center" width="70%" colspan="4"><strong>合计</strong></td>
								<td align="right" width="30%" colspan="3"><s:property
									value="userCount1" /></td>
							</tr>
						</table>
					</s:if> <s:elseif test="#report1Flg == 2">
						<table width="500" border="0" align="left" cellpadding="1"
							cellspacing="1">
							<tr>
								<td align="left" colspan="5"><strong>没有相关记录，请修改查询条件再次检索！</strong></td>
							</tr>
						</table>
					</s:elseif></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="900" align="left" cellpadding="1">
				<tr height="20">
					<td colspan="7">
					<hr color="green">
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">统计年份</td>
					<td nowrap="nowrap" width="200"><input type="text"
						class="Wdate" id="reportYear" readOnly name="reportYear"
						value='<s:property value="reportYear" />'
						onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})"
						tabindex="10" /></td>
					<td colspan="5" width="700">&nbsp;&nbsp;&nbsp;&nbsp;<input
						type="button" id="searchButton2" name="searchButton2" value="查询"
						onClick="searchByYear()" tabindex="11">&nbsp;&nbsp;<input
						type="reset" name="reset" value="重置" onclick="reSet()"
						tabindex="12"></td>
				</tr>
				<tr height="30">
					<td colspan="5"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td><s:set name="report2Flg" value="%{report2Flg}"></s:set> <s:if
				test="#report2Flg == 1">
				<table width="900" border="1" align="left" cellpadding="1"
					cellspacing="0">
					<tr>
						<td align="center" width="70%" colspan="4"><strong>月份</strong></td>
						<td align="center" width="30%" colspan="3"><strong>注册数量</strong></td>
					</tr>
					<s:iterator status="st" value="report2List">
						<tr>
							<td nowrap colspan="4" align="center"><s:property
								value="month" /></td>
							<td nowrap colspan="3" align="right"><s:property
								value="regNum" /></td>
						</tr>
					</s:iterator>
					<tr>
						<td align="center" width="70%" colspan="4"><strong>合计</strong></td>
						<td align="right" width="30%" colspan="3"><s:property
							value="userCount2" /></td>
					</tr>
				</table>
			</s:if> <s:elseif test="#report2Flg == 2">
				<table width="900" border="0" align="left" cellpadding="1"
					cellspacing="1">
					<tr>
						<td align="left" colspan="5"><strong>没有相关记录，请修改查询条件再次检索！</strong></td>
					</tr>
				</table>
			</s:elseif></td>
		</tr>
		<tr>
			<td height="50">
			</td>
		</tr>
	</table>					
</s:form>
</body>
</html>