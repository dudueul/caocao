using System.Collections.Generic;
using UnityEngine;

namespace MAssist
{
	public class MCommandDataScrollView : MDragableScrollView
	{
		public List<MCommandItem> itemList;

		public MCommandDataScrollView(float itemHeight, GUIStyle style, Color color)
			: base(0f, null, default(Color))
		{
		}

		public override void GotoEndOfScroll()
		{
		}

		protected override void SetScrollViewPosition()
		{
		}

		protected override void OnGUIScrollViewInside()
		{
		}
	}
}
