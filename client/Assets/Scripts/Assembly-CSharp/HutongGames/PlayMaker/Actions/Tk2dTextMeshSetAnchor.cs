using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dTextMeshSetAnchor : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public TextAnchor textAnchor;

		public FsmString OrTextAnchorString;

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

		private void DoSetAnchor()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}

		private TextAnchor getTextAnchorFromString(string textAnchorString, out bool isValid)
		{
			isValid = default(bool);
			return TextAnchor.UpperLeft;
		}
	}
}
