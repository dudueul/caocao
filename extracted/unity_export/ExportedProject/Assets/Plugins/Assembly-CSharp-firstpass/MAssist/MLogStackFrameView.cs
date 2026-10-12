using System;
using UnityEngine;

namespace MAssist
{
	[Serializable]
	public class MLogStackFrameView
	{
		[SerializeField]
		private Vector2 _stackFrameScrollViewPosition;

		[SerializeField]
		protected Vector2 _deltaDrag;

		[SerializeField]
		protected bool _isDrag;

		public bool isDrag
		{
			get
			{
				return false;
			}
		}

		public void OnGUI(MLogData selectedLogData)
		{
		}

		private void checkDrag()
		{
		}
	}
}
