using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetTextureScale : ComponentAction<Renderer>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmInt materialIndex;

		public FsmString namedTexture;

		[RequiredField]
		public FsmFloat scaleX;

		[RequiredField]
		public FsmFloat scaleY;

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

		private void DoSetTextureScale()
		{
		}
	}
}
