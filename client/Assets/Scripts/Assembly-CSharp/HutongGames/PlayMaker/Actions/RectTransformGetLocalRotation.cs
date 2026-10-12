using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformGetLocalRotation : BaseUpdateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector3 rotation;

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
