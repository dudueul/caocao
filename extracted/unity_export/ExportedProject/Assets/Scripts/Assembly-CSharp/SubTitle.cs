using System.Collections;
using System.Diagnostics;

public class SubTitle : SelfRegister2UIComponentsMonobehaviour
{
	public UILabelLocalized m_labelTop;

	public UILabelLocalized m_labelBottom;

	private const float m_fTimeInterval = 1f;

	private float m_fLastTime;

	private float m_fInterval;

	private void Update()
	{
	}

	public void SetTitle(int nChapter, string sText)
	{
	}

	[DebuggerHidden]
	private IEnumerator DisappearDesc(float fInterval)
	{
		return null;
	}
}
