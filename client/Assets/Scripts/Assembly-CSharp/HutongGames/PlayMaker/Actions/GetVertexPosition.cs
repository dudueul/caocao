using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetVertexPosition : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt vertexIndex;

		public Space space;

		[RequiredField]
		public FsmVector3 storePosition;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetVertexPosition()
		{
		}
	}
}
