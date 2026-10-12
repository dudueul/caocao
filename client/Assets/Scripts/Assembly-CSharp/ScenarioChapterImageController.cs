using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class ScenarioChapterImageController : MonoBehaviour
{
	private int m_currentChapterNo;

	private UITexture imgTexture
	{
		get
		{
			return null;
		}
	}

	private UITextureMaterialAnimation textureMaterialAnimation
	{
		get
		{
			return null;
		}
	}

	public int currentChapterNo
	{
		get
		{
			return 0;
		}
		private set
		{
		}
	}

	public void SetCurrentChapterNo(int chapterNo)
	{
	}

	public void ResetTextureMaterialAnimation()
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateChapterImage()
	{
		return null;
	}
}
