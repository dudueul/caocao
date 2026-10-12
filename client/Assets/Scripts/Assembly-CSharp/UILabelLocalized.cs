using System.Collections.Generic;
using System.Text;
using UnityEngine;

[ExecuteInEditMode]
public class UILabelLocalized : UILabel
{
	private class AppendCtx
	{
		public string formatStr;

		public object[] args;

		public AppendCtx(string formatStr, object[] args)
		{
		}
	}

	private static StringBuilder ms_sbForAppendCtx;

	private static StringBuilder ms_sbForInternal;

	private static readonly string ms_newLine;

	[SerializeField]
	private string localizeFormat;

	[SerializeField]
	private object[] formatArgs;

	private List<AppendCtx> appendCtxes;

	private string __builtString;

	private bool dirty;

	private UIInput m_input;

	private bool isNeedTranslate;

	private bool isReplaceJosa;

	private bool isAppendFormatted;

	public string builtString
	{
		get
		{
			return null;
		}
	}

	public string key
	{
		get
		{
			return null;
		}
	}

	public object[] args
	{
		get
		{
			return null;
		}
	}

	public new string text
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private new void OnEnable()
	{
	}

	private new void Start()
	{
	}

	protected override void OnUpdate()
	{
	}

	public void SetTransalatable(bool isTranslatable)
	{
	}

	public void SetKey(string format)
	{
	}

	public void SetContext(string format, params object[] args)
	{
	}

	public void StartFormat()
	{
	}

	public void AppendFormat(string format, params object[] args)
	{
	}

	public void AppendLine()
	{
	}

	public void EndFormat()
	{
	}

	private void Build()
	{
	}

	private void BuildInternal(ref string srcFormat, ref object[] srcArgs, ref string result)
	{
	}

	private void LocalizeIfNeeded()
	{
	}

	public void LocalizeNow()
	{
	}

	private void OnLocalize()
	{
	}
}
