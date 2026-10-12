using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dTextMeshSetProperties : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString text;

		public FsmBool inlineStyling;

		public TextAnchor anchor;

		public FsmString OrTextAnchorString;

		public FsmBool kerning;

		public FsmInt maxChars;

		public FsmInt NumDrawnCharacters;

		public FsmColor mainColor;

		public FsmColor gradientColor;

		public FsmBool useGradient;

		public FsmInt textureGradient;

		public FsmVector3 scale;

		public FsmBool commit;

		private tk2dTextMesh _textMesh;

		private void _getTextMesh()
		{
		}

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetProperties()
		{
		}
	}
}
