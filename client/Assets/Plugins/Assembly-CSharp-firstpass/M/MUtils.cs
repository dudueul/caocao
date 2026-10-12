using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

namespace M
{
	public static class MUtils
	{
		public enum TypeFlag
		{
			NONE = 0,
			CLASS = 1,
			IGNORE_UNITY = 2,
			IGNORE_SYSTEM = 4
		}

		private static DateTime _prevMouseDownTime;

		private static Vector2 _prevMouseDownPosition;

		private static DateTime _prevMouseDownTimeWithRect;

		private static Vector2 _prevMouseDownPositionWithRect;

		public static string GetProjectName()
		{
			return null;
		}

		public static bool CheckGUIDoubleClick(Vector2 mousePosition, float threshold = 10f, float limitTime = 0.3f)
		{
			return false;
		}

		public static bool CheckGUIDoubleClickWithRect(Rect clickableRect, Vector2 mousePosition, float threshold = 10f, float limitTime = 0.3f)
		{
			return false;
		}

		public static Vector3 notZeroVector(Vector3 vector, float delta = 0.01f)
		{
			return default(Vector3);
		}

		public static void setColliderEnable(GameObject target, bool isEnable)
		{
		}

		public static Transform Search(Transform target, string name)
		{
			return null;
		}

		public static void Activate(Transform t)
		{
		}

		public static void ActivateAllChildren(Transform t)
		{
		}

		[DebuggerHidden]
		public static IEnumerator ActivateWithCoroutine(MonoBehaviour mb)
		{
			return null;
		}

		[DebuggerHidden]
		public static IEnumerator ActivateAllChildrenWithCoroutine(MonoBehaviour mb, Transform t)
		{
			return null;
		}

		public static void DeActivate(Transform t)
		{
		}

		public static void DeActivateAllChildren(Transform t)
		{
		}

		public static void DestroyAllChidren(GameObject parent)
		{
		}

		public static void DestroyAllChidren(Transform parent)
		{
		}

		public static void DestroyAll(List<GameObject> list)
		{
		}

		public static void ListFill(IList list, int capacity, object fillObject)
		{
		}

		public static void ReEnableBoxCollider(GameObject go)
		{
		}

		public static void AllCameraEnable(bool isEnable)
		{
		}

		public static Vector3 ToLocalDeltaFromScreenDelta(Camera camera, Vector2 screenDelta, Transform trans)
		{
			return default(Vector3);
		}

		public static Vector3 ToLocalPosFromScreenPos(Camera camera, Vector2 screenPos, Transform trans)
		{
			return default(Vector3);
		}

		public static Dictionary<string, List<Type>> GetTypesByName(string className, out int count, bool ignoreCase, bool contains, bool ignoreUnity = true, bool ignoreSystem = true, bool onlyClass = true)
		{
			count = default(int);
			return null;
		}
	}
}
