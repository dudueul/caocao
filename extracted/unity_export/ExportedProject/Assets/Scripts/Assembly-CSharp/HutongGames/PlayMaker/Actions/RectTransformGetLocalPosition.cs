using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformGetLocalPosition : BaseUpdateAction
	{
		public enum LocalPositionReference
		{
			Anchor = 0,
			CenterPosition = 1
		}

		[RequiredField]
		public FsmOwnerDefault gameObject;

		public LocalPositionReference reference;

		public FsmVector3 position;

		public FsmVector2 position2d;

		public FsmFloat x;

		public FsmFloat y;

		public FsmFloat z;

		private RectTransform _rt;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void DoGetValues()
		{
		}
	}
}
