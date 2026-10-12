using UnityEngine.SceneManagement;

namespace HutongGames.PlayMaker.Actions
{
	public class MergeScenes : FsmStateAction
	{
		public GetSceneActionBase.SceneAllReferenceOptions sourceReference;

		public FsmInt sourceAtIndex;

		public FsmString sourceByName;

		public FsmString sourceByPath;

		public FsmOwnerDefault sourceByGameObject;

		public GetSceneActionBase.SceneAllReferenceOptions destinationReference;

		public FsmInt destinationAtIndex;

		public FsmString destinationByName;

		public FsmString destinationByPath;

		public FsmOwnerDefault destinationByGameObject;

		public FsmBool success;

		public FsmEvent successEvent;

		public FsmEvent failureEvent;

		private Scene _sourceScene;

		private bool _sourceFound;

		private Scene _destinationScene;

		private bool _destinationFound;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void GetSourceScene()
		{
		}

		private void GetDestinationScene()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
