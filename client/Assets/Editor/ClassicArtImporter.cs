using UnityEditor;
using UnityEngine;

namespace Rebuild.Classic.EditorSupport
{
    // Import settings for the 1998 pixel art exported by scripts/ekd5/export_art.py
    // into Assets/Resources/ClassicArt: crisp pixels, exact sizes, no compression.
    // Frames are cut at runtime by ClassicArt (48 px = 1 world unit).
    public class ClassicArtImporter : AssetPostprocessor
    {
        const string Root = "Assets/Resources/ClassicArt/";

        static bool IsClassic(string path)
        {
            return path.Replace('\\', '/').StartsWith(Root, System.StringComparison.OrdinalIgnoreCase);
        }

        void OnPreprocessTexture()
        {
            if (!IsClassic(assetPath)) return;
            var ti = (TextureImporter)assetImporter;
            ti.textureType = TextureImporterType.Default;
            ti.filterMode = FilterMode.Point;
            ti.mipmapEnabled = false;
            ti.npotScale = TextureImporterNPOTScale.None;
            ti.wrapMode = TextureWrapMode.Clamp;
            ti.textureCompression = TextureImporterCompression.Uncompressed;
            ti.alphaSource = TextureImporterAlphaSource.FromInput;
            ti.alphaIsTransparency = true;
            ti.maxTextureSize = 4096;   // battle maps are up to 1920 px
            ti.isReadable = false;
        }

        void OnPreprocessAudio()
        {
            if (!IsClassic(assetPath)) return;
            var ai = (AudioImporter)assetImporter;
            var s = ai.defaultSampleSettings;
            s.loadType = AudioClipLoadType.DecompressOnLoad;   // short effects
            ai.defaultSampleSettings = s;
        }

        [MenuItem("Tools/Classic Art/Reimport exported art")]
        static void Reimport()
        {
            AssetDatabase.ImportAsset(Root.TrimEnd('/'), ImportAssetOptions.ImportRecursive | ImportAssetOptions.ForceUpdate);
        }
    }
}
