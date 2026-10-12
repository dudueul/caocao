using System.Diagnostics;
using UnityEngine;

namespace Thingsoft
{
	public class NGUIUtil
	{
		public static Bounds CalculateRelativeSelfWidgetBounds(Transform relativeTo, Transform content, bool considerInactive, bool considerParents = true)
		{
			return default(Bounds);
		}

		[DebuggerHidden]
		[DebuggerStepThrough]
		private static void CalculateRelativeSelfWidgetBounds(Transform content, bool considerInactive, bool isRoot, ref Matrix4x4 toLocal, ref Vector3 vMin, ref Vector3 vMax, ref bool isSet, bool considerParents)
		{
		}

		public static int CalculateNextPanelDepth(GameObject go)
		{
			return 0;
		}

		public static Bounds CalculateSelfAbsoluteWidgetBounds(Transform trans)
		{
			return default(Bounds);
		}
	}
}
