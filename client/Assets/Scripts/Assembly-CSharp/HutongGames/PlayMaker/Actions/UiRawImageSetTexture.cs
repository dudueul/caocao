using UnityEngine;
using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiRawImageSetTexture : ComponentAction<RawImage>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmTexture texture;

		public FsmBool resetOnExit;

		private RawImage _texture;

		private Texture _originalTexture;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetValue()
		{
		}

		public override void OnExit()
		{
		}
	}
}
