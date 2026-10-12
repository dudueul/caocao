using System.Collections.Generic;
using UnityEngine;

public class ColliderUtil
{
	private static void SortMaskingAreas(ref List<Rect> visbleAreaRects)
	{
	}

	public static List<GameObject> CreateBlockedCollider(GameObject parent, Rect baseRect, List<Rect> visbleAreaRects)
	{
		return null;
	}

	private static void DivideAreaWithRectangle(Rect baseArea, ref List<Rect> visbleAreaRects, List<Rect> nonVisibleAreaRects)
	{
	}

	private static void SplitMaskingRectByIntersectedArea(Rect origin, Rect intersection, ref List<Rect> visbleAreaRects)
	{
	}

	private static List<Rect> DivideAreaImpl(Rect r1, Rect r2)
	{
		return null;
	}

	private static Rect GetIntersectArea(Rect r1, Rect r2)
	{
		return default(Rect);
	}

	public static List<GameObject> CreateGameObjectsWithCollider(GameObject parent, List<Rect> targetRects, string prefixName)
	{
		return null;
	}
}
