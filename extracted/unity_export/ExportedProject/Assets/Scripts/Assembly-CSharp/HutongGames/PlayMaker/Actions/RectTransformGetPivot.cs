using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformGetPivot : BaseUpdateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector2 pivot;

		public FsmFloat x;

		public FsmFloat y;

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
