using UnityEngine;

namespace HutongGames.PlayMaker
{
	public class PlayMakerCanvasRaycastFilterProxy : MonoBehaviour, ICanvasRaycastFilter
	{
		public bool RayCastingEnabled;

		public bool IsRaycastLocationValid(Vector2 sp, Camera eventCamera)
		{
			return false;
		}
	}
}
